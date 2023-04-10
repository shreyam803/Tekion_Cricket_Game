package com.week4_cricket.game.service;

import com.week4_cricket.game.models.Match;
import com.week4_cricket.game.models.Team;
import com.week4_cricket.game.repo.MatchRepo;
import com.week4_cricket.game.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ScoreboardService scoreboardService;

    Scanner sc = new Scanner(System.in);

    public Match addMatch(Match match) throws Exception{
        String team1Id = match.getTeam1Id();
        String team2Id = match.getTeam2Id();
        int over = match.getOver();
        if (team1Id == null || team2Id == null || team1Id.isEmpty() || team2Id.isEmpty() || over == 0){
            throw new Exception("Team1Id, Team2Id & over cannot be null");
        }
        Optional<Team> team1Data = teamRepo.findById(team1Id);
        Optional<Team> team2Data = teamRepo.findById(team2Id);
        if (team1Data.isEmpty() || team2Data.isEmpty())
            throw new Exception("Team1Id or team2Id are not valid");
        List<String> team1 = team1Data.get().getPlayers();
        List<String> team2 = team2Data.get().getPlayers();
        if (team1 == null || team2 == null || team1.size() != team2.size())
            throw new Exception("team1 and team2 size should be same");
        return matchRepo.save(match);
    }
    public List<Match> getAllMatches(){
        return matchRepo.findAll();
    }
    public Match getMatchById(String id) throws Exception{
        if(id == null || id.isEmpty())
            throw new Exception("MatchId cannot be null and Empty");
        return matchRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }
    public void startMatch(Match match){
        Team team1 = teamRepo.findById(match.getTeam1Id()).get();
        Team team2 = teamRepo.findById(match.getTeam2Id()).get();
        Team batting = toss(team1, team2);
        Team bowling;
        if(batting == team1) bowling = team2;
        else bowling = team1;

        match.setBattingTeamId(batting.getTeamId());
        match.setBowlingTeamId(bowling.getTeamId());
        int target = matchSimulator(match, batting, match.getOver(), 0);
        matchSimulator(match, bowling, match.getOver(), target+1);
        matchRepo.save(match);
    }
    private Team toss(Team team1, Team team2) {
        int toss = (int) (Math.random() * 2);
        int choice[] = {1, 2};
        int decision = choice[(int) (Math.random() * 2)];
        return ((toss == 0 && decision == 1) || (toss == 1 && decision == 2)) ? team1 : team2;
    }
    private int matchSimulator(Match match, Team team, int overSize, int target) {
        int total4s = 0;
        int total6s = 0;
        int ballPlayed = 0;
        int totalScore = 0;
        int wickets = 0;
        int teamScore = 0;
        int size = team.getPlayers().size();  // size of the team
        List<String> teamPlayer = team.getPlayers(); // Fetching the playerIds
        System.out.println(teamPlayer);
        List<List<Integer>> scorePerOver = new ArrayList<>();
        String p = teamPlayer.get(wickets);
        System.out.println(p);
        for (int over = 0; over < overSize && wickets < size - 1; over++) {
            System.out.println(over + " over ");
            ArrayList<Integer> runPerOver = new ArrayList<>();
            for (int ball = 0; ball < 6 && wickets < size - 1; ball++) {
                int runPerBall = playerService.runsMadeByPlayer(p);
                System.out.println(runPerBall + "run per Ball");
                ballPlayed++;
                runPerOver.add(runPerBall);
                if (runPerBall == 0) {
                    wickets++;
                    System.out.println(p + "playerId" + total4s + "total4s" + total6s + "total6s" + ballPlayed + "ballPlayed" + totalScore + "totalScore");
                    playerService.updateScore(p, total4s, total6s, ballPlayed, totalScore);
                    total4s = 0;
                    total6s = 0;
                    ballPlayed = 0;
                    totalScore = 0;
                    p = teamPlayer.get(wickets);
                } else {
                    if (runPerBall == 4) total4s++;
                    else if (runPerBall == 6) total6s++;
                    totalScore += runPerBall;
                    teamScore += runPerBall;
                }
                if (match.getBowlingTeamId().equals(team.getTeamId()) && teamScore > target) break;
            }
            System.out.println(p + "playerId" + total4s + "total4s" + total6s + "total6s" + ballPlayed + "ballPlayed" + totalScore + "totalScore");
            playerService.updateScore(p, total4s, total6s, ballPlayed, totalScore);
            scorePerOver.add(runPerOver);
            if (match.getBowlingTeamId().equals(team.getTeamId()) && teamScore > target) break;
        }
        scoreboardService.updateScoreBoard(match, scorePerOver, wickets, teamScore, team.getTeamId());
        return teamScore;
    }
}

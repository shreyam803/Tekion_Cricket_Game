package com.week4_cricket.game.service;

import com.week4_cricket.game.models.Match;
import com.week4_cricket.game.models.Scoreboard;
import com.week4_cricket.game.repo.ScoreboardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreboardService {
    @Autowired
    private ScoreboardRepo scoreboardRepo;

    public Scoreboard getScoreboard(String id) throws Exception{
        if(id == null || id.isEmpty())
            throw new Exception("Id cannot be null and Empty");
        return scoreboardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scoreboard is not found"));
    }
    public void updateScoreBoard(Match match, List<List<Integer>> scorePerOver, int wickets, int teamScore, String teamId) {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setTotalScore(teamScore);
        scoreboard.setWicketsTaken(wickets);
        scoreboard.setScorePerBall(scorePerOver);
        scoreboard.setTeamId(teamId);
        Scoreboard score = scoreboardRepo.save(scoreboard);
        System.out.println(score.getId()+" score of them ");
        if(teamId.equals(match.getTeam1Id())) match.setScoreboard1Id(score.getId());
        else
            match.setScoreboard2Id(score.getId());
    }
    public void matchResult(Match match,String scoreboard1Id, String scoreboard2Id) {
        Scoreboard team1score = scoreboardRepo.findById(scoreboard1Id)
                .orElseThrow(() -> new RuntimeException("Scoreboard1Id is not found"));
        Scoreboard team2score = scoreboardRepo.findById(scoreboard2Id)
                .orElseThrow(() -> new RuntimeException("Scoreboard2Id is not found"));
        if(team1score.getTotalScore() > team2score.getTotalScore()) match.setWinningTeamId(team1score.getTeamId());
        else if(team1score.getTotalScore()< team2score.getTotalScore()) match.setWinningTeamId(team2score.getTeamId());

    }
}

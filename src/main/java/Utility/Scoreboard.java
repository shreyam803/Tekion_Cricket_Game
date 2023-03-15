package Utility;

import Entity.Player;
import Entity.Team;
import Enum.TeamStatus;
import Service.MatchService;
import Service.PlayerService;
import Service.TeamService;

import java.util.List;

public class Scoreboard {
    private PlayerService playerService;
    private TeamService teamService;
    private MatchService matchService;
    public Scoreboard(){
        playerService = new PlayerService();
        teamService = new TeamService();
        matchService = new MatchService();
    }
    public void teamScore(String matchId){
        String team1Id = matchService.findMatchById(matchId).getTeam1Id();
        String team2Id = matchService.findMatchById(matchId).getTeam2Id();
        Team team1 = teamService.getTeam(team1Id);
        Team team2 = teamService.getTeam(team2Id);
        scoreBoard(team1, team2);
        scorePerOver(team1.getTeamStatus().equals(TeamStatus.BATTING.name()) ? (team1) : (team2));
        scorePerOver(team2.getTeamStatus().equals(TeamStatus.BOWLING.name()) ? (team2) : (team1));
    }

    public void scoreBoard(Team team1, Team team2){
        System.out.println("************************************ Match Result ************************************");
        System.out.println("Total score of " + team1.getName() + " is: " + team1.getScore() + " run at wickets " + team1.getWickets() + " out of " + team1.getPlayers().size());
        System.out.println("Total score of " + team2.getName() + " is: " + team2.getScore() + " run at wickets " + team2.getWickets() + " out of " + team2.getPlayers().size());
        if(team1.getScore() > team2.getScore()){
            System.out.println(team1.getTeamStatus().equals(TeamStatus.BATTING.name()) ? team1.getName() + " has won the match by " + (team1.getScore() - team2.getScore()) + " runs." :
                    team1.getName() + " has won the match by " + (team1.getPlayers().size() - team1.getWickets())+ " wickets.");
            saveTeamStatus(team1, team2, false);
        }
        else if(team1.getScore() == team2.getScore()){
            System.out.println("Tie between " + team1.getName() + " and " + team2.getName());
            saveTeamStatus(team1, team2, true);
        }
        else{
            System.out.println(team2.getTeamStatus().equals(TeamStatus.BATTING.name()) ? team2.getName() + " has won the match by " + (team2.getScore() - team1.getScore()) + " runs." :
                    team2.getName() + " has won the match by " + (team2.getPlayers().size() - team2.getWickets())+ " wickets.");
            saveTeamStatus(team2, team1, false);
        }
    }
    public void scorePerOver(Team team){
        System.out.println("************************************Score Of " + team.getName() + " ************************************");
        int c = 1;
        for(List<Integer> over : team.getScorePerOver()){
            System.out.println("Over : " + c);
            for(int runPerBall : over){
                if(runPerBall == 0){
                    System.out.println("W ");
                }
                else{
                    System.out.println(runPerBall + " ");
                }
            }
            c++;
            System.out.println();
            System.out.println();
        }
        playerScoreBoard(team.getPlayers());
    }
    public void playerScoreBoard(List<String> playerIds){
        for(String id : playerIds){
            Player p = playerService.getPlayer(id);
            if(p.getTotalBattingScore() > 0 || p.getBallsPlayed() > 0){
                System.out.println("Runs made by " + p.getName() + " : " + p.getTotalBattingScore());
                System.out.println("Total 4s made by " + p.getName() + " : " + p.getFoursScored());
                System.out.println("Total 6s made by " + p.getName() + " : " + p.getSixesScored());
                System.out.println("Total Balls played by " + p.getName() + " : " + p.getBallsPlayed());
                System.out.println("***********************************************************************");
            }
        }
    }
    public void saveTeamStatus(Team winner, Team loser, boolean isTie){
        if(isTie == true){
            teamService.updateTeamStatus(winner.get_id(), TeamStatus.TIE.name());
            teamService.updateTeamStatus(loser.get_id(), TeamStatus.TIE.name());
        }
        else{
            teamService.updateTeamStatus(winner.get_id(), TeamStatus.WINNER.name());
            teamService.updateTeamStatus(loser.get_id(), TeamStatus.LOSER.name());
        }
    }
}

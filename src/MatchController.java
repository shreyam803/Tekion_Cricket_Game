import java.util.ArrayList;
import java.util.Scanner;

public class MatchController {
    Scanner sc = new Scanner(System.in);
    public Team toss(Team team1, Team team2){
        int toss = (int) (Math.random() * 2);
        if (toss == 1) {
            System.out.println("Toss won by team " + team1.getName());
            System.out.println("Choose 1 for batting and 2 for bowling.");
            int decision = sc.nextInt();
            if(decision == 1){
                System.out.println(team1.getName() + " decided to bat first.");
                return team1;
            }
            return team2;
        }
        else{
            System.out.println("Toss won by team " + team2.getName());
            System.out.println("Choose 1 for batting and 2 for bowling.");
            int decision = sc.nextInt();
            if(decision == 1){
                System.out.println(team2.getName() + " decided to bat first.");
                return team2;
            }
            return team1;
        }
    }

    public void matchSimulator(Team team, int numOfOvers, int target){
        int totalFours = 0;
        int totalSixes = 0;
        int ballsPlayed = 0;
        int wickets = 0;
        int playerScore = 0;
        int teamScore = 0;
        Double run;

        int teamSize = team.getPlayers().size();
        ArrayList<Player> teamPlayer = team.getPlayers();
        Player p = teamPlayer.get(wickets);
        ArrayList<ArrayList<Integer>> scorePerOver = new ArrayList<>();

        for(int over = 0; over < numOfOvers && wickets < teamSize-1; over++){
            ArrayList<Integer> runPerOver = new ArrayList<>();
            for(int ball = 0; ball < 6 && wickets < teamSize -1; ball++){
                run = Math.random();
                int runPerBall = runsMadeByPlayer(p, run);
                ballsPlayed++;
                runPerOver.add(runPerBall);
                if(runPerBall == 7){
                    wickets++;
                    setPlayerData(p, totalFours, totalSixes, ballsPlayed, playerScore);
                    totalFours = 0;
                    totalSixes = 0;
                    ballsPlayed = 0;
                    playerScore = 0;
                    p = teamPlayer.get(wickets);
                }
                else {
                    if(runPerBall == 4) totalFours++;
                    else if(runPerBall == 6) totalSixes++;
                    playerScore += runPerBall;
                    teamScore += runPerBall;
                }
                if(team.getInning() == 2 && target <= teamScore) {
                    team.setScorePerOver(scorePerOver);
                    team.setWickets(wickets);
                    team.setScore(teamScore);
                    setPlayerData(p, totalFours, totalSixes, ballsPlayed, playerScore);
                    scorePerOver.add(runPerOver);
                    return;
                }
            }
            setPlayerData(p, totalFours, totalSixes, ballsPlayed, playerScore);
            scorePerOver.add(runPerOver);

        }
        team.setScorePerOver(scorePerOver);
        team.setWickets(wickets);
        team.setScore(teamScore);
    }

    public int runsMadeByPlayer(Player p, Double run){
        PlayerRole[] playerRole = PlayerRole.values();
        if(p.getPlayerRole().equals(playerRole[0])){
            if(run > 0 && run <= 0.1){
                return 1;
            }
            else if(run > 0.1 && run < 0.2){
                return 2;
            }
            else if(run >= 0.2 && run < 0.3){
                return 3;
            }
            else if(run >= 0.3 && run < 0.5){
                return 4;
            }
            else if(run >= 0.5 && run < 0.7){
                return 6;
            }
            else if(run >= 0.7 && run < 0.8){
                return 7;
            }
            else {
                return 5;
            }
        }
        else {
            if(run > 0 && run <= 0.2){
                return 1;
            }
            else if(run > 0.2 && run < 0.4){
                return 2;
            }
            else if(run >= 0.4 && run < 0.5){
                return 3;
            }
            else if(run >= 0.5 && run < 0.8){
                return 7;
            }
            else if(run >= 0.8 && run < 0.85){
                return 6;
            }
            else if(run >= 0.85 && run < 0.9){
                return 4;
            }
            else {
                return 5;
            }
        }
    }
    public void setPlayerData(Player p, int totalFours, int totalSixes, int ballsPlayed, int playerScore){
        p.setTotalFoursScored(totalFours);
        p.setTotalSixesScored(totalSixes);
        p.setTotalBallsPlayed(ballsPlayed);
        p.setTotalBattingRuns(playerScore);
    }
}

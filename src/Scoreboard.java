import java.util.ArrayList;
public class Scoreboard {
    public void displayTeamScore(Team team){
        System.out.println("************* Scoreboard of " + team.getName() + "****************");
        int count = 1;
        for (ArrayList<Integer> over : team.getScorePerOver()) {
            System.out.println("In Over : " + count);
            for (int run : over) {
                if (run == 7)
                    System.out.print("W ");
                else
                    System.out.print(run + " ");
            }
            count++;
            System.out.println();
            System.out.println();
        }
        for(Player p : team.getPlayers()){
            if(p.getTotalBattingRuns() > 0 || p.getTotalBallsPlayed() > 0){
                System.out.println("Runs made by " + p.getName() + " : " + p.getTotalBattingRuns());
                System.out.println("Total 4s made by " + p.getName() + " : " + p.getTotalFoursScored());
                System.out.println("Total 6s made by " + p.getName() + " : " + p.getTotalSixesScored());
                System.out.println("Total Balls played by " + p.getName() + " : " + p.getTotalBallsPlayed());
                System.out.println("*********************************************************");
            }
        }
    }

    public void scoreBoard(Team team1, Team team2){
        System.out.println("************************ Final Match Results *********************");
        System.out.println("Score of "+ team1.getName()+" is " + team1.getScore() + " with " + team1.getWickets() + " wickets.");
        System.out.println("Score of "+ team2.getName()+" is " + team2.getScore() + " with " + team2.getWickets() + " wickets.");

        if(team1.getScore() > team2.getScore()){
            System.out.println(team1.getName() + " won the match by " + team1.getScore() + " runs.");
        }
        else if(team1.getScore() == team2.getScore()){
            System.out.println("Equal Scores!! Match Tie.");
        }
        else {
            System.out.println(team2.getName()+ " won the match by " + team2.getScore() + " runs.");
        }
    }
}

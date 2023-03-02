//PRINTING THE SCOREBOARD
public class Scoreboard {
    public void scoreboard(Teams team1, Teams team2){
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

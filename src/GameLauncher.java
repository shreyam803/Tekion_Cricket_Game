import java.util.Scanner;

public class GameLauncher {
    Scanner sc = new Scanner(System.in);
    //START OF THE GAME
    public void startGame() throws InterruptedException{
        System.out.println("Enter the number of overs:");
        int numOfOvers = sc.nextInt();
        sc.nextLine();
        System.out.println("number of overs:" + numOfOvers);

        System.out.println("Enter the name of team 1: ");
        String name1 = sc.nextLine();

        System.out.println("Enter the name of team 2: ");
        String name2 = sc.nextLine();

        MatchController match = new MatchController();
        Teams team1 = new Teams();
        Teams team2 = new Teams();
        team1.setName(name1);
        team2.setName(name2);

        Teams batting = match.toss(team1,team2);

        match.play(numOfOvers, batting,0);

        Teams bowling;

        if (batting == team1) {
            bowling = team2;
        }
        else{
            bowling = team1;
        }

        match.play(numOfOvers, bowling, batting.getScore()+1);

        Scoreboard scores = new Scoreboard();
        scores.scoreboard(team1, team2);

    }

}

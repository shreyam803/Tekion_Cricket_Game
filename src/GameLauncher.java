import java.util.ArrayList;
import java.util.Scanner;

public class GameLauncher {
    Scanner sc = new Scanner(System.in);
    //START OF THE GAME
    public void startGame() {
        System.out.println("Enter the number of overs:");
        int numOfOvers = sc.nextInt();
        sc.nextLine();
        System.out.println("number of overs:" + numOfOvers);

        System.out.println("Enter the name of team 1: ");
        String name1 = sc.nextLine();

        System.out.println("Enter the name of team 2: ");
        String name2 = sc.nextLine();

        Team team1 = new Team();
        team1.setName(name1);

        Team team2 = new Team();
        team2.setName(name2);

        PlayerRole[] playerRole = PlayerRole.values();
        Player p1 = new Player();
        p1.setName("Rohit Sharma");
        p1.setPlayerRole(playerRole[0]);
        Player p2 = new Player();
        p2.setName("Virat Kohli");
        p2.setPlayerRole(playerRole[0]);
        Player p3 = new Player();
        p3.setName("Ravindra Jadeja");
        p3.setPlayerRole(playerRole[1]);
        Player p4 = new Player();
        p4.setName("Jasprit Bumbrah");
        p4.setPlayerRole(playerRole[1]);
        Player p5 = new Player();
        p5.setName("Shikhar Dhawan");
        p5.setPlayerRole(playerRole[0]);


        ArrayList<Player> team1Players = new ArrayList<>();
        team1Players.add(p1);
        team1Players.add(p2);
        team1Players.add(p3);
        team1Players.add(p4);
        team1Players.add(p5);
        team1.setPlayers(team1Players);

        Player p6 = new Player();
        p6.setName("Mohammad Yousuf");
        p6.setPlayerRole(playerRole[0]);
        Player p7 = new Player();
        p7.setName("Shahid Afridi");
        p7.setPlayerRole(playerRole[0]);
        Player p8 = new Player();
        p8.setName("Shoaib Akhtar");
        p8.setPlayerRole(playerRole[1]);
        Player p9 = new Player();
        p9.setName("Imran Khan");
        p9.setPlayerRole(playerRole[1]);
        Player p10 = new Player();
        p10.setName("Younis Khan");
        p10.setPlayerRole(playerRole[0]);

        ArrayList<Player> team2Players = new ArrayList<>();
        team2Players.add(p6);
        team2Players.add(p7);
        team2Players.add(p8);
        team2Players.add(p9);
        team2Players.add(p10);
        team2.setPlayers(team2Players);

        MatchController match = new MatchController();

        Team batting = match.toss(team1,team2);
        Team bowling;


        if (batting == team1) {
            bowling = team2;
        }
        else{
            bowling = team1;
        }
        batting.setInning(1);
        match.matchSimulator(batting, numOfOvers,0);

        bowling.setInning(2);
        match.matchSimulator(bowling, numOfOvers, batting.getScore()+1);

        Scoreboard scores = new Scoreboard();
        scores.displayTeamScore(team1);
        scores.displayTeamScore(team2);
        scores.scoreBoard(team1, team2);

    }

}

import java.util.Scanner;

public class MatchController {
    Scanner sc = new Scanner(System.in);
    public Teams toss(Teams team1, Teams team2){
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

    public void play(int numOfOvers, Teams team, int target) throws InterruptedException{
        int wicket=0;
        int score = 0;
        System.out.println("Scoreboard of team " + team.getName() + " :");
        for(int i=0;i<numOfOvers && wicket<10;i++){
            System.out.println("Score of over " + (i+1));
            for(int j=0;j<6 && wicket<10;j++){
                team.over[j] = (int)(Math.random()*8);
//hello
                if(team.over[j] == 7){
                    System.out.print("W" + " ");
                    wicket++;
                    team.setWickets(wicket);
                    continue;
                }
                System.out.print(team.over[j] + " ");
                score += team.over[j];

                team.setScore(score);
                //if(wicket == 10) break;
                if(team.getInning() == 2 && target < score) {
                    System.out.println("");
                    return;
                }
            }
            Thread.sleep(1000);
            System.out.println("");
            //if(wicket == 10) break;
        }
        Thread.sleep(1500);

    }
}

import java.util.ArrayList;

public class Team {
    private String teamId;
    private String name;
    private int inning;
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<ArrayList<Integer>> scorePerOver;
    private int wickets;
    private int score;
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<ArrayList<Integer>> getScorePerOver() {
        return scorePerOver;
    }

    public void setScorePerOver(ArrayList<ArrayList<Integer>> scorePerOver) {
        this.scorePerOver = scorePerOver;
    }
    public void setWickets(int wickets){
        this.wickets = wickets;
    }

    public int getWickets(){
        return this.wickets;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }

}

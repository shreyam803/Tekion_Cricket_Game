public class Teams {
    private String name;
    int[] over = new int[6];
    private int inning;
    private int wickets;
    private int score;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setInning(int inning){
        this.inning = inning;
    }
    public int getInning(){
        return this.inning;
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

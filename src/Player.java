public class Player {
    private String id;
    private String name;
    private String teamId;
    PlayerRole playerRole;
    private int totalBallsPlayed, totalFoursScored, totalSixesScored;
    private int totalBattingRuns, totalWicketsTaken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public PlayerRole getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }

    public int getTotalBallsPlayed() {
        return totalBallsPlayed;
    }

    public void setTotalBallsPlayed(int totalBallsPlayed) {
        this.totalBallsPlayed = totalBallsPlayed;
    }

    public int getTotalFoursScored() {
        return totalFoursScored;
    }

    public void setTotalFoursScored(int totalFoursScored) {
        this.totalFoursScored = totalFoursScored;
    }

    public int getTotalSixesScored() {
        return totalSixesScored;
    }

    public void setTotalSixesScored(int totalSixesScored) {
        this.totalSixesScored = totalSixesScored;
    }

    public int getTotalBattingRuns() {
        return totalBattingRuns;
    }

    public void setTotalBattingRuns(int totalBattingRuns) {
        this.totalBattingRuns = totalBattingRuns;
    }

    public int getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public void setTotalWicketsTaken(int totalWicketsTaken) {
        this.totalWicketsTaken = totalWicketsTaken;
    }
}

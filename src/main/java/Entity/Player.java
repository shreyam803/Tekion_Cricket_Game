package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String _id;
    private String name;
    private String teamId;
    private String playerRole;
    private int ballsPlayed, foursScored, sixesScored;
    private int totalBattingScore;
}

package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String _id;
    private String name;
    private int score;
    private int wickets;
    private String teamStatus;
    private List<List<Integer>> scorePerOver;
    private List<String> players;
}

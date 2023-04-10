package com.week4_cricket.game.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Match {
    @Id
    private String matchId;
    private String team1Id;
    private String team2Id;
    private String battingTeamId;
    private String bowlingTeamId;
    private String winningTeamId;
    private int over;
    private String scoreboard1Id;
    private String scoreboard2Id;
}

package com.week4_cricket.game.models;

import com.week4_cricket.game.enums.PlayerRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Player {
    @Id
    private String playerId;
    private String playerName;
    private PlayerRole playerRole;
    private String teamId;
    private int ballsPlayed, foursScored, sixesScored;
    private int totalBattingScore;
}

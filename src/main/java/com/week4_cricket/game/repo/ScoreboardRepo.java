package com.week4_cricket.game.repo;

import com.week4_cricket.game.models.Scoreboard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreboardRepo extends MongoRepository<Scoreboard, String> {
}

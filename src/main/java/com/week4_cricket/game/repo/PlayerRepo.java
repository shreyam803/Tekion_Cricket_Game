package com.week4_cricket.game.repo;

import com.week4_cricket.game.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepo extends MongoRepository<Player, String> {
}

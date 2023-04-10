package com.week4_cricket.game.repo;

import com.week4_cricket.game.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepo extends MongoRepository<Match, String> {
}

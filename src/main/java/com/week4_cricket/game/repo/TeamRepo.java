package com.week4_cricket.game.repo;

import com.week4_cricket.game.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepo extends MongoRepository<Team, String> {
}

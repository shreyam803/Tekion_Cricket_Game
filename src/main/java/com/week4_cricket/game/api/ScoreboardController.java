package com.week4_cricket.game.api;

import com.week4_cricket.game.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoreboard")
public class ScoreboardController {
    @Autowired
    private ScoreboardService scoreboardService;
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getScoreboard(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(scoreboardService.getScoreboard(id), HttpStatus.OK);
    }
}

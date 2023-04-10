package com.week4_cricket.game.api;

import com.week4_cricket.game.models.Match;
import com.week4_cricket.game.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/startMatch")
    public void startMatch(@RequestBody Match match) {
        matchService.startMatch(match);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addMatch(@RequestBody Match match) throws Exception{
        return new ResponseEntity<>(matchService.addMatch(match), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMatches(){
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMatchById(@PathVariable String id) throws Exception{
        return new ResponseEntity<>(matchService.getMatchById(id), HttpStatus.OK);
    }
}

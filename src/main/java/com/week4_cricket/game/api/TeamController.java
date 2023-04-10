package com.week4_cricket.game.api;

import com.week4_cricket.game.models.Match;
import com.week4_cricket.game.models.Team;
import com.week4_cricket.game.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/add")
    public ResponseEntity<Object> addTeam(@RequestBody Team team) throws Exception {
        return new ResponseEntity<>(teamService.addTeam(team), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllTeams() {
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getTeamById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @PutMapping("/updatePlayers/{teamId}/{playerId}")
    public ResponseEntity<Object> updateTeamPlayers(@PathVariable String teamId, @PathVariable String playerId) throws Exception {
        return new ResponseEntity<>(teamService.updateTeamPlayers(teamId, playerId), HttpStatus.OK);
    }

}

package com.week4_cricket.game.api;

import com.week4_cricket.game.models.Player;
import com.week4_cricket.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<Object> addPlayer(@RequestBody Player player) throws Exception{
        return new ResponseEntity<>(playerService.addPlayer(player), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllPlayers(){
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getPlayerById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }
}

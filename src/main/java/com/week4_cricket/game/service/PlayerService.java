package com.week4_cricket.game.service;

import com.week4_cricket.game.enums.PlayerRole;
import com.week4_cricket.game.models.Player;
import com.week4_cricket.game.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class PlayerService {
  @Autowired
  private PlayerRepo playerRepo;

  public Player addPlayer(Player player) throws Exception{
      if(player.getPlayerName() == null || player.getPlayerRole() == null || player.getPlayerName().isEmpty() || player.getPlayerRole().toString().isEmpty()){
          throw new Exception("Player name and Player role cannot be null or Empty");
      }
      return playerRepo.save(player);
  }
  public List<Player> getAllPlayers(){
      return playerRepo.findAll();
  }
  public Player getPlayerById(String id) throws Exception{
      if(id == null || id.isEmpty())
          throw new Exception("PlayerId cannot be null and Empty");
      return playerRepo.findById(id)
              .orElseThrow(() -> new RuntimeException("Player not found"));
  }


  public void updateScore(String id, int foursScored, int sixesScored, int ballsPlayed, int totalScore){
      playerRepo.findById(id).map(player -> {
          player.setTotalBattingScore(totalScore + player.getTotalBattingScore());
          player.setFoursScored(foursScored + player.getFoursScored());
          player.setSixesScored(sixesScored + player.getSixesScored());
          player.setBallsPlayed(ballsPlayed + player.getBallsPlayed());
          return playerRepo.save(player);
      });
  }
  public int runsMadeByPlayer(String playerId){
      Random r = new Random();
      Player player = playerRepo.findById(playerId).get();
      int probability = r.nextInt(100);
      int run = r.nextInt(7);
      System.out.println();
      if(player.getPlayerRole().equals(PlayerRole.BATSMAN.name())){
          return probability < 60 ? 6 : run;
      }
      else{
          return probability < 60 ? 2 : run;
      }

  }
}

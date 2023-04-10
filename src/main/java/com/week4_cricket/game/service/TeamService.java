package com.week4_cricket.game.service;

import com.week4_cricket.game.models.Player;
import com.week4_cricket.game.models.Team;
import com.week4_cricket.game.repo.PlayerRepo;
import com.week4_cricket.game.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;
    public Team addTeam(Team team) throws Exception{
        if(team.getTeamName() == null || team.getTeamName().isEmpty()){
            throw new Exception("Team Name cannot be null");
        }
        return teamRepo.save(team);
    }
    public List<Team> getAllTeams(){
        return teamRepo.findAll();
    }
    public Team updateTeamPlayers(String teamId, String playerId) throws Exception{
        if(teamId == null || playerId == null || teamId.isEmpty() || playerId.isEmpty()){
            throw new Exception("TeamId and PlayerId cannot be null and Empty");
        }
        Optional<Player> playerData = playerRepo.findById(playerId);
        if(playerData.isEmpty())
            throw new Exception("Player with this id is not found");
        Player player = playerData.get();
        if(player.getTeamId() != null)
            throw new Exception("Player is already added to the another team");
        Optional<Team> teamData = teamRepo.findById(teamId);
        if(teamData.isEmpty())
            throw new Exception("Team with Id is not found");
        Team team = teamData.get();
        List<String> teamPlayer;
        if (team.getPlayers() == null) teamPlayer = new ArrayList<>();
        else {
            if (isPlayerPresent(playerId, team.getPlayers()))
                throw new Exception("Player Id already present");
            teamPlayer = team.getPlayers();
        }
        teamPlayer.add(playerId);
        team.setPlayers(teamPlayer);
        player.setTeamId(teamId);
        playerRepo.save(player);
        return teamRepo.save(team);
    }
    public Team getTeamById(String id) throws Exception{
        if(id == null || id.isEmpty())
            throw new Exception("TeamId cannot be null and Empty");
        return teamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

    }
    public boolean isPlayerPresent(String playerId, List<String> player){
        for(String id : player){
            if(id.equals(playerId)) return true;
        }
        return false;
    }
}

package Service;

import Connection.DbConnection;
import Entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import Enum.PlayerRole;
import org.bson.conversions.Bson;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class PlayerService {
    public static MongoCollection<Document> playerCollection;
    public static ObjectMapper objectMapper;
    public PlayerService(){
        MongoDatabase db = DbConnection.getDatabase();
        playerCollection = db.getCollection("Player");
        objectMapper = new ObjectMapper();
    }
    public String addPlayer(String name, PlayerRole playerRole, String teamId){
        UUID uuid = UUID.randomUUID();
        String playerId = uuid.toString();
        Document player = new Document("_id",playerId);
        player.append("name", name)
                .append("teamId", teamId)
                .append("playerRole", playerRole.name())
                .append("ballsPlayed", 0)
                .append("foursScored", 0)
                .append("sixesScored", 0)
                .append("totalBattingScore",0);
        playerCollection.insertOne(player);
        return playerId;
    }
    public void updateScore(String id, int foursScored, int sixesScored, int ballsPlayed, int totalScore){
        Bson filter = eq("_id",id);
        playerCollection.updateOne(filter,
                Updates.combine(
                        Updates.set("foursScored", foursScored),
                        Updates.set("sixesScored", sixesScored),
                        Updates.set("ballsPlayed", ballsPlayed),
                        Updates.set("totalBattingScore", totalScore)));
    }
    public Player getPlayer(String id){
        Bson filter = eq("_id",id);
        Document document = playerCollection.find(filter).first();
        Player player = null;
        try{
            player = objectMapper.readValue(document.toJson(), Player.class);
        }
        catch (Exception e){
            System.out.println("Error !");
        }
        return player;
    }
}

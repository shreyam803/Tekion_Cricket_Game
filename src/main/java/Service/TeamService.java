package Service;

import Connection.DbConnection;
import Entity.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class TeamService{
    private static MongoCollection<Document> teamCollection;
    private static ObjectMapper objectMapper;
    public TeamService(){
        MongoDatabase db = DbConnection.getDatabase();
        teamCollection = db.getCollection("Team");
        objectMapper = new ObjectMapper();
    }
    public String addTeam(String name){
        UUID uuid = UUID.randomUUID();
        String teamId = uuid.toString();
        Document team = new Document("_id", teamId);
        team.append("name", name)
                .append("score", 0)
                .append("wickets", 0)
                .append("teamStatus", "")
                .append("players", new ArrayList<String>())
                .append("scorePerOver", new ArrayList<>());
        teamCollection.insertOne(team);
        return teamId;
    }
    public Team getTeam(String teamId){
        Bson filter = eq("_id", teamId);
        Document document = teamCollection.find(filter).first();
        Team team = null;
        try{
            team = objectMapper.readValue(document.toJson(), Team.class);
        }
        catch (Exception e){
            System.out.println("Error !");
        }
        return team;
    }
    public void updatePlayerList(String id, List<String> playerIds){
        Bson filter = eq("_id", id);
        teamCollection.updateOne(filter, Updates.set("players", playerIds));
    }
    public void setTeamScore(Team team){
        Bson filter = eq("_id", team.get_id());
        teamCollection.updateOne(filter, Updates.combine(
                Updates.set("wickets", team.getWickets()),
                Updates.set("score", team.getScore()),
                Updates.set("scorePerOver", team.getScorePerOver())));
    }
    public void updateTeamStatus(String id, String status){
        Bson filter = eq("_id", id);
        teamCollection.updateOne(filter, Updates.set("teamStatus", status));
    }
}

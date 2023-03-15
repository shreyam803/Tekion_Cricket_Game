package Connection;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DbConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static String url = "mongodb://localhost:27017/";
    private static String databaseName = "CricketGame";
    public static MongoDatabase getDatabase() throws MongoException{
        if(mongoDatabase == null){
            mongoClient = MongoClients.create(url);
            mongoDatabase = mongoClient.getDatabase(databaseName);
        }
        return mongoDatabase;
    }
    public static void close(){
        if(mongoClient != null){
            mongoClient.close();
            mongoClient = null;
            databaseName = null;
        }
    }
}

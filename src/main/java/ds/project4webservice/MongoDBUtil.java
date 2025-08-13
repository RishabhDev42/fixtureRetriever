package ds.project4webservice;
// Rishabh Devgon | rishabhd

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

//Utility class to handle MongoDB connection and operations
public class MongoDBUtil {
    private static final String CONNECTION_STRING = "mongodb+srv://kevinmessiah7:S0C5qUXkVHFIcKxc@cluster0.2cpgg.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "project4logs";
    private static final String COLLECTION_NAME = "logs";

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

//    Static block to initialize MongoDB connection
    static {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .serverApi(serverApi)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    // Method to insert a log into the MongoDB collection
    public static void insertLog(Document log) {
        collection.insertOne(log);
    }

    // Method to retrieve all logs from the MongoDB collection
    public static List<Document> getLogs() {
        return collection.find().into(new ArrayList<>());
    }

    // Method to retrieve the database from the MongoDB collection
    public static MongoDatabase getDatabase() {
        return database;
    }
}

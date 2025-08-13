package ds.project4webservice;
// Rishabh Devgon | rishabhd

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

// Service class to handle analytics calculations
public class AnalyticsService {

    public static List<Document> getAnalytics() {
        List<Document> analytics = new ArrayList<>();
        MongoDatabase database = MongoDBUtil.getDatabase();
        MongoCollection<Document> collection = database.getCollection("logs");

        // Top 5 phone models making requests
        Document topPhoneModels = new Document("name", "Top 5 Phone Models")
                .append("value", collection.aggregate(List.of(
                        new Document("$group", new Document("_id", "$phoneModel").append("count", new Document("$sum", 1))),
                        new Document("$match", new Document("_id", new Document("$ne", null))),
                        new Document("$sort", new Document("count", -1)),
                        new Document("$limit", 5)
                )).into(new ArrayList<>()));

        // Top 5 teams making requests
        Document topTeams = new Document("name", "Top 5 Teams")
                .append("value", collection.aggregate(List.of(
                        new Document("$group", new Document("_id", "$teamId").append("count", new Document("$sum", 1))),
                        new Document("$match", new Document("_id", new Document("$ne", null))),
                        new Document("$sort", new Document("count", -1)),
                        new Document("$limit", 5)
                )).into(new ArrayList<>()));

        // Top 5 date ranges making requests
        Document topDateRanges = new Document("name", "Top 5 Date Ranges")
                .append("value", collection.aggregate(List.of(
                        new Document("$project", new Document("dateRange", new Document("$concat", List.of("$startDate", " to ", "$endDate")))),
                        new Document("$group", new Document("_id", "$dateRange").append("count", new Document("$sum", 1))),
                        new Document("$match", new Document("_id", new Document("$ne", null))),
                        new Document("$sort", new Document("count", -1)),
                        new Document("$limit", 5)
                )).into(new ArrayList<>()));

        analytics.add(topPhoneModels);
        analytics.add(topTeams);
        analytics.add(topDateRanges);

        return analytics;
    }
}
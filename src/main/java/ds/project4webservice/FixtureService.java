package ds.project4webservice;
// Rishabh Devgon | rishabhd

import DataClass.APIResponse;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// FixtureService class to handle API requests for fixtures and teams to SportMonks API (3rd party API)
public class FixtureService {
    private static final String BASE_URL = "https://api.sportmonks.com/v3/football/";

    public static APIResponse getFixtures(int teamId, String startDate, String endDate) {
        String urlString = BASE_URL+"fixtures/between/"+startDate+"/"+endDate+"/"+teamId;
        return doGet(urlString);
    }

    public static APIResponse getTeams() {
        String urlString = BASE_URL+"teams/countries/320";
        return doGet(urlString);
    }

    public static APIResponse doGet(String urlString){
        APIResponse apiResponse = new APIResponse();
        try {
//            Open an HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Add headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Z7pdX0CvE3F1q6op2jl1w6FJETtPhGGgFRWprXFhbjkLJoLogq5kfX3o0oQT");

            // Log request details
            long requestTimestamp = System.currentTimeMillis();
            MongoDBUtil.insertLog(new Document()
                    .append("requestPath", urlString)
                    .append("requestMethod", "GET")
                    .append("timestamp", requestTimestamp));

//            Get the response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                apiResponse.setStatus(responseCode);
                apiResponse.setMessage(response.toString());

                // Log response details
                long responseTimestamp = System.currentTimeMillis();
                MongoDBUtil.insertLog(new Document()
                        .append("requestPath", urlString)
                        .append("requestMethod", "GET")
                        .append("responseStatus", responseCode)
                        .append("responseBody", response.toString())
                        .append("timestamp", responseTimestamp));

                // Print the response
                System.out.println(response);
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return apiResponse;
    }

}

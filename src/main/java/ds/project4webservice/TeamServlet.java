package ds.project4webservice;
// Rishabh Devgon | rishabhd

import java.io.*;

import DataClass.APIResponse;
import DataClass.TeamClientResponse;
import DataClass.TeamResponse;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bson.Document;

import static ds.project4webservice.ServiceUtils.getTeamClientList;

@WebServlet(name = "teamServlet", value = "/teams")
public class TeamServlet extends HttpServlet {
//   Provides team data for the mobile app
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();


        // Log request details
        String phoneModel = request.getHeader("User-Agent");
        String requestParams = request.getQueryString();
        long timestamp = System.currentTimeMillis();
        MongoDBUtil.insertLog(new Document()
                .append("requestPath", request.getRequestURI())
                .append("requestMethod", request.getMethod())
                .append("phoneModel", phoneModel)
                .append("requestParams", requestParams)
                .append("timestamp", timestamp));

//        Get team data from SportMonks API
        APIResponse apiResponse = FixtureService.getTeams();
        String jsonResponse = apiResponse.getMessage();
        TeamResponse teamResponse = gson.fromJson(jsonResponse, TeamResponse.class);
//        Convert team data to client response format simplified for mobile
        TeamClientResponse res = new TeamClientResponse(getTeamClientList(teamResponse.getData()));

        // Log response details
        String mobileResponse = gson.toJson(res);
        MongoDBUtil.insertLog(new Document()
                .append("requestPath", request.getRequestURI())
                .append("requestMethod", request.getMethod())
                .append("responseStatus", response.getStatus())
                .append("responseBody", mobileResponse)
                .append("timestamp", System.currentTimeMillis()));

//       Send the response back to the client
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(res));
    }

    public void destroy() {
    }
}
package ds.project4webservice;
// Rishabh Devgon | rishabhd

import java.io.*;

import DataClass.*;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bson.Document;

import static ds.project4webservice.ServiceUtils.getFixtureClientList;

@WebServlet(name = "fixtureServlet", value = "/fixtures")
public class FixtureServlet extends HttpServlet {

//    Provides fixture data for a specific team given the team ID and date range
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();

        // Log request details
        String phoneModel = request.getHeader("User-Agent");
        String requestParams = request.getQueryString();
        long timestamp = System.currentTimeMillis();
        MongoDBUtil.insertLog(new Document()
                .append("requestPath", request.getRequestURI())
                .append("teamId", teamId)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .append("requestMethod", request.getMethod())
                .append("phoneModel", phoneModel)
                .append("requestParams", requestParams)
                .append("timestamp", timestamp));

// Get fixture data from SportMonks API
        APIResponse apiResponse = FixtureService.getFixtures(teamId, startDate, endDate);
        String jsonResponse = apiResponse.getMessage();
        FixtureResponse fixtureResponse = gson.fromJson(jsonResponse, FixtureResponse.class);
//        Convert fixture data to client response format simplified for mobile
        FixtureClientResponse res = new FixtureClientResponse(getFixtureClientList(fixtureResponse.getData()));

        // Log response details
        String mobileResponse = gson.toJson(res);
        MongoDBUtil.insertLog(new Document()
                .append("requestPath", request.getRequestURI())
                .append("requestMethod", request.getMethod())
                .append("requestParams", requestParams)
                .append("responseStatus", response.getStatus())
                .append("responseBody", mobileResponse)
                .append("timestamp", System.currentTimeMillis()));

//        Send the response back to the client
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(res));
    }

    public void destroy() {
    }
}
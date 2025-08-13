<%@ page import="org.bson.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="ds.project4webservice.MongoDBUtil" %>
<%@ page import="ds.project4webservice.AnalyticsService" %>
<!DOCTYPE html>
<html>
<head>
    <title>Operations Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Operations Dashboard</h1>
    <h2>Operations Analytics</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Analytics</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Document> analytics = AnalyticsService.getAnalytics();
            for (Document doc : analytics) {
        %>
        <tr>
            <td><%= doc.getString("name") %></td>
            <td><%= doc.get("value") %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <h2>Logs</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Request Path</th>
            <th>Request Method</th>
            <th>Phone Model</th>
            <th>Request Params</th>
            <th>Response Status</th>
            <th>Response Body</th>
            <th>Timestamp</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Document> logs = MongoDBUtil.getLogs();
            for (Document log : logs) {
        %>
        <tr>
            <td><%= log.getString("requestPath") %></td>
            <td><%= log.getString("requestMethod") %></td>
            <td><%= log.getString("phoneModel") %></td>
            <td><%= log.getString("requestParams") %></td>
            <td><%= log.getInteger("responseStatus") %></td>
            <td><%= log.getString("responseBody") %></td>
            <td><%= log.getLong("timestamp") %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
package com.example;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String keyword = request.getParameter("keyword");
            keyword = keyword.toLowerCase();

            Connection connection = DatabaseConnection.getConnection();

            ResultSet resultSet = connection.createStatement()
                    .executeQuery(
                            "SELECT pageTitle, pageLink, (LENGTH (LOWER(pageData))-LENGTH(REPLACE(LOWER(pageData), '"
                                    + keyword
                                    + "', '')))/length('" + keyword
                                    + "') AS countoccurence FROM pages ORDER BY countoccurence DESC LIMIT 30");

            ArrayList<searchResult> results = new ArrayList<searchResult>();
            while (resultSet.next()) {
                searchResult searchResult = new searchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO `history`(name, link) VALUES (?,?)");
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, "http://localhost:8080/demo/MyServlet?keyword=" + keyword);
            preparedStatement.executeUpdate();

            request.setAttribute("results", results);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>This is my super servlet  " + keyword + "  </h3>");

        } catch (IOException | SQLException ioException) {
            System.out.println(ioException);
        }
    }
}

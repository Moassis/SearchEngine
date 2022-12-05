package com.example;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class history extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // void func() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM history");
            ArrayList<historySearch> results = new ArrayList<>();
            while (resultSet.next()) {
                historySearch historyResult = new historySearch();
                historyResult.setName(resultSet.getString("name"));
                historyResult.setLink(resultSet.getString("link"));
                results.add(historyResult);
            }

            for (historySearch historyResult : results) {
                System.out.println(historyResult.getName() + " " + historyResult.getLink() + "\n");
            }

            request.setAttribute("results", results);
            request.getRequestDispatcher("history.jsp").forward(request, response);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Data base not conn");
        }

    }

    // public static void main(String[] args) {
    // history ob = new history();
    // ob.func();
    // }
}

package com.example;

import java.sql.*;

public class DatabaseConnection {

    static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        String db = "acciosearch";
        String user = "root";
        String pas = "";
        return getConnection(db, user, pas);
    }

    private static Connection getConnection(String db, String user, String pas) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/" + db + "?user=" + user + "&password=" + pas);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database not connected......");
        }
        return connection;
    }
}

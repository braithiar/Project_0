package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
  private static Connection conn = null;
  public static Connection getConnection() throws SQLException {
    // Legacy driver registration
    /*try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      System.out.println("Could not locate driver.");
    }*/

    String url = System.getenv("URL");
    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");

    if (conn == null || conn.isClosed()) {
      conn = DriverManager.getConnection(url, username, password);
      System.out.println("***New Connection Made***");
      return conn;
    }

    System.out.println("***Existing Connection Used***");
    return conn;
  }
}

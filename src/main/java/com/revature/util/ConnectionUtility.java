package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
  private static Connection conn;

  private ConnectionUtility() {
  }

  public static Connection getConnection() throws SQLException {
    // Legacy driver registration
    /*try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      System.out.println("Could not locate driver.");
    }*/

    String url =
      "jdbc:postgresql://localhost:5432/postgres?currentSchema=project_0";
    String username = "postgres";
    String password = "v19%%Admin55!!";

    if (conn != null && !conn.isClosed()) {
      System.out.println("Existing conn");
      return conn;
    }

    System.out.println("New conn");
    conn = DriverManager.getConnection(url, username, password);

    return conn;
  }
}

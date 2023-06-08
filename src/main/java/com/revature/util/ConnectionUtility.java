package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
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

    return DriverManager.getConnection(url, username, password);
  }
}

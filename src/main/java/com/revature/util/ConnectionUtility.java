package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    Properties prop = new Properties();
    String url;
    String username;
    String password;

    try {
      prop.load(new FileReader("src/main/resources/application.properties"));

      url = prop.getProperty("url");
      username = prop.getProperty("username");
      password = prop.getProperty("password");
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }

    if (conn != null && !conn.isClosed()) {
      System.out.println("Existing conn");
      return conn;
    }

    System.out.println("New conn");
    conn = DriverManager.getConnection(url, username, password);

    return conn;
  }
}

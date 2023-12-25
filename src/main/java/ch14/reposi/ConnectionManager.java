package ch14.reposi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

  private static Connection connection;

  public static Connection getConnection() {
    return connection;
  }

  // @formatter:off
  public static Connection openConnection() {
    try {
      connection = DriverManager.getConnection(
          "jdbc:h2:mem:passenger", "sa", "");
      return connection;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  // @formatter:on

  public static void closeConnection() {
    if (null != connection) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

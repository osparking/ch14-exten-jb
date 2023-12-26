package ch14.reposi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {

  public static void createTable(Connection connection) {
    String sql = "create table if not exists passengers "
        + "(id varchar(50), NAME VARCHAR(50));";
    executeStatement(connection, sql);
  }

  public static void dropTable(Connection connection) {
    String sql = "drop table if exists passengers;";
    executeStatement(connection, sql);
  }

  private static void executeStatement(Connection connection, String sql) {
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

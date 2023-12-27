package ch14.passen.extension;

import java.sql.Connection;
import java.sql.Savepoint;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import ch14.reposi.ConnectionManager;
import ch14.reposi.TablesManager;

public class DataOperationExtension implements BeforeEachCallback,
    AfterEachCallback, BeforeAllCallback, AfterAllCallback {

  private Connection connection;
  private Savepoint savepoint;

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ConnectionManager.closeConnection();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    connection = ConnectionManager.getConnection();
    TablesManager.dropTable(connection);
    TablesManager.createTable(connection);
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    connection.rollback(savepoint);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    connection.setAutoCommit(false);
    savepoint = connection.setSavepoint("savepoint");
  }
}

package ch14.reposi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch14.passen.Passenger;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PassengerDaoImpl implements PassengerDao {

  private Connection connection;

  @Override
  public void insert(Passenger passenger) throws PassengerExistsException {
    String sql = "INSERT INTO PASSENGERS (ID, NAME) VALUES (?, ?)";
    Passenger existingPassenger = getById(passenger.getIdentifier());

    if (existingPassenger != null) {
      throw new PassengerExistsException(existingPassenger,
          existingPassenger.toString());
    }

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, passenger.getIdentifier());
      statement.setString(2, passenger.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(String id, String name) {
    String sql = "UPDATE PASSENGERS SET NAME = ? WHERE ID = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setString(2, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(Passenger passenger) {
    String sql = "DELETE FROM PASSENGERS WHERE ID = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, passenger.getIdentifier());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Passenger getById(String id) {
    String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";
    Passenger passenger = null;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        passenger = new Passenger(rs.getString(1), rs.getString(2));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return passenger;
  }
}

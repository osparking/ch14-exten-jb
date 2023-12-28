package ch14.reposi;

import java.sql.SQLException;

import ch14.passen.Passenger;

public class PassengerExistsException extends SQLException {
  private Passenger passenger;

  public PassengerExistsException(Passenger passenger, String message) {
    super(message);
    this.passenger = passenger;
  }
}

package ch14.reposi;

import ch14.passen.Passenger;

public interface PassengerDao {
  public void insert(Passenger passenger);

  public void update(String id, String name);

  public void delete(Passenger passenger);

  public Passenger getById(String id);
}

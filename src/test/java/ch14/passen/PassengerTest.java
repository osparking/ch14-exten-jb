package ch14.passen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ch14.passen.extension.ContextExecutionCondition;
import ch14.passen.extension.DataAccessObjectParameterResolver;
import ch14.passen.extension.DataOperationExtension;
import ch14.passen.extension.LogPassengerExistsExceptionExtension;
import ch14.reposi.PassengerDao;
import ch14.reposi.PassengerExistsException;
import lombok.AllArgsConstructor;

@ExtendWith({ ContextExecutionCondition.class, DataOperationExtension.class })
@ExtendWith({ DataAccessObjectParameterResolver.class })
@ExtendWith({ LogPassengerExistsExceptionExtension.class })
@AllArgsConstructor
class PassengerTest {

  private PassengerDao passengerDao;

  @Test
  void testPassengerString() {
    var passenger = new Passenger("KR345", "이길동");
    var passengerLee = "승객 [아이디=KR345, 성명=이길동]";
    assertEquals(passengerLee, passenger.toString());
  }

  @Test
  void testPassengerInsert() throws PassengerExistsException {
    String custID = "123-345-345";
    String custName = "김만기";

    Passenger passenger = new Passenger(custID, custName);
    passengerDao.insert(passenger);
    assertEquals(custName, passengerDao.getById(custID).getName());
  }
  
  @Test
  void testPassengerUpdate() throws PassengerExistsException {
    String custID = "123-345-345";
    String custName = "김만기";
    String newName = "이봉창";
    
    Passenger passenger = new Passenger(custID, custName);
    passengerDao.insert(passenger);
    passengerDao.update(custID, newName);
    assertEquals(newName, passengerDao.getById(custID).getName());
  }
  
  @Test
  void testPassengerDelete() throws PassengerExistsException {
    String custID = "123-345-345";
    String custName = "김만기";
    
    Passenger passenger = new Passenger(custID, custName);
    passengerDao.insert(passenger);
    passengerDao.delete(passenger);
    assertNull(passengerDao.getById(custID));
  }
}

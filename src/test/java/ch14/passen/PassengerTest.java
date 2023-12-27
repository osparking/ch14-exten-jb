package ch14.passen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ch14.passen.extension.ContextExecutionCondition;
import ch14.passen.extension.DataOperationExtension;
import ch14.reposi.PassengerDao;
import lombok.AllArgsConstructor;

@ExtendWith({ ContextExecutionCondition.class, DataOperationExtension.class })
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
  void testPassengerInsert() {
    String custID = "123-345-345";
    String custName = "김만기";

    Passenger passenger = new Passenger(custID, custName);
    passengerDao.insert(passenger);
    assertEquals(custName, passengerDao.getById(custID).getName());
  }
}

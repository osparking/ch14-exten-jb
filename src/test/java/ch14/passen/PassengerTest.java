package ch14.passen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ch14.passen.extension.ContextExecutionCondition;

@ExtendWith(ContextExecutionCondition.class)
class PassengerTest {

  @Test
  void testPassengerString() {
    var passenger = new Passenger("KR345", "이길동");
    var passengerLee = "승객 [아이디=KR345, 성명=이길동]";
    assertEquals(passengerLee, passenger.toString());
  }
}

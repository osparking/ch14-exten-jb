package ch14.passen;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Passenger {
  private String identifier;
  private String name;

  @Override
  public String toString() {
    return "승객 [아이디=" + identifier + ", 성명=" + name + "]";
  }
}

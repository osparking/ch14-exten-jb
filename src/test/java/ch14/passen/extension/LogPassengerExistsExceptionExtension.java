package ch14.passen.extension;

import java.util.logging.Logger;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import ch14.reposi.PassengerExistsException;
import space.jbpark.utility.MyUtil;

public class LogPassengerExistsExceptionExtension
    implements TestExecutionExceptionHandler {
  private Logger logger = MyUtil.getLogger(this.getClass().getName());

  @Override
  public void handleTestExecutionException(ExtensionContext context,
      Throwable throwable) throws Throwable {
    if (throwable instanceof PassengerExistsException) {
      logger.severe("다음 같은 승객이 있습니다: " + throwable.getMessage());
      return;
    }
    throw throwable;
  }

}

package ch14.passen.extension;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ContextExecutionCondition implements ExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(
      ExtensionContext context) {
    Properties properties = new Properties();
    String executionContext = "";

    try {
      properties.load(ContextExecutionCondition.class.getClassLoader()
          .getResourceAsStream("context.properties"));
      executionContext = properties.getProperty("context");
      if (!"regular".equalsIgnoreCase(executionContext)
          && !"low".equalsIgnoreCase(executionContext)) {
        return ConditionEvaluationResult
            .disabled(executionContext + " 승객 수가 시험을 불능화했다");
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return ConditionEvaluationResult
        .enabled(executionContext + " 승객 수에서 시험이 활성화되었다");
  }
}

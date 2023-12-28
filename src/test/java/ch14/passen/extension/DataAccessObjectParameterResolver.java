package ch14.passen.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import ch14.reposi.ConnectionManager;
import ch14.reposi.PassengerDao;
import ch14.reposi.PassengerDaoImpl;

public class DataAccessObjectParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().equals(PassengerDao.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    return new PassengerDaoImpl(ConnectionManager.getConnection());
  }

}

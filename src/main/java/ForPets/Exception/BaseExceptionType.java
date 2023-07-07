package ForPets.Exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
  int getErrorCode();

  HttpStatus getHttpStatus();

  String getErrorMessage();
}

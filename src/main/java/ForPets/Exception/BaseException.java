package ForPets.Exception;

public abstract class BaseException extends RuntimeException{
  public abstract BaseExceptionType getExceptionType();
}

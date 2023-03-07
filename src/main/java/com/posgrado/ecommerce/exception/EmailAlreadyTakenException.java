package com.posgrado.ecommerce.exception;

public class EmailAlreadyTakenException extends RuntimeException {

  public static final String ERROR_MESSAGE = "Email %s already taken";

  public EmailAlreadyTakenException(String email) {
    super(String.format(ERROR_MESSAGE, email));
  }
}

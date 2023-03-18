package com.posgrado.ecommerce.exception;

public class NameRoleAlreadyTakenException extends RuntimeException {

  public static final String ERROR_MESSAGE = "Role with name %s already exists";

  public NameRoleAlreadyTakenException(String nameRole) {
    super(String.format(ERROR_MESSAGE, nameRole));
  }
}

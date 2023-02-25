package com.posgrado.ecommerce.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

  public static final String MESSAGE_ERROR = "%s with %s not found";

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String entity, UUID id) {
    super(String.format(MESSAGE_ERROR, entity, id.toString()));
  }
}

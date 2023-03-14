package com.posgrado.ecommerce.exception.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorResponse {

  private String message;
  private List<FieldErrorModel> errors;

  public ValidationErrorResponse() {
    this.errors = new ArrayList<>();
  }
}

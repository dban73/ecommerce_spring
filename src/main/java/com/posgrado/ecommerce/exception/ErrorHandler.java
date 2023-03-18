package com.posgrado.ecommerce.exception;

import com.posgrado.ecommerce.exception.response.ErrorResponse;
import com.posgrado.ecommerce.exception.response.FieldErrorModel;
import com.posgrado.ecommerce.exception.response.ValidationErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse response = new ErrorResponse(status.value(), status.name(), ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(EmailAlreadyTakenException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyTakenException(Exception ex) {
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse response = new ErrorResponse(status.value(), status.name(), ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception ex) {
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    ErrorResponse response = new ErrorResponse(status.value(), status.name(), ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<FieldErrorModel> errors = ex.getBindingResult().getAllErrors().stream()
        .map(fieldError -> {
          FieldErrorModel fieldErrorModel = new FieldErrorModel();
          fieldErrorModel.setField(((FieldError) fieldError).getField());
          fieldErrorModel.setMessage(fieldError.getDefaultMessage());
          fieldErrorModel.setCode(fieldError.getCode());
          return fieldErrorModel;
        }).toList();

    ValidationErrorResponse response = new ValidationErrorResponse();
    response.setErrors(errors);
    response.setMessage(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(NameRoleAlreadyTakenException.class)
  public ResponseEntity<ErrorResponse> handleNameRoleAlreadyTakenException(Exception ex) {
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse response = new ErrorResponse(status.value(), status.name(), ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }
}

package com.davidson.helpdesk.resource.exception;

import com.davidson.helpdesk.services.exception.DataIntegrityViolationException;
import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjnotfoundException.class)
  public ResponseEntity<StandardError> objnotfoundException(ObjnotfoundException ex,
                                                            HttpServletRequest request) {
    StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
            "Objeto não encontrado", ex.getMessage(), request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                            HttpServletRequest request) {
    StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
            "Violação de dados.", ex.getMessage(), request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
                                                                       HttpServletRequest request) {
    ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
            "Validation Field", "Erro na validação dos campos.", request.getRequestURI());

    for(FieldError x: ex.getBindingResult().getFieldErrors()){
      errors.addError(x.getField(), ex.getFieldError().getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}

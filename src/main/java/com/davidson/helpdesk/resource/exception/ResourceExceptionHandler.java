package com.davidson.helpdesk.resource.exception;

import com.davidson.helpdesk.services.exception.ObjnotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}

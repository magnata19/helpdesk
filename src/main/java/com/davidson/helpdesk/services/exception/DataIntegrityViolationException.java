package com.davidson.helpdesk.services.exception;

public class DataIntegrityViolationException extends RuntimeException {
  public DataIntegrityViolationException(String message) {
    super(message);
  }
}

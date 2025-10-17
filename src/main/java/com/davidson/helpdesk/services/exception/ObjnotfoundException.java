package com.davidson.helpdesk.services.exception;

public class ObjnotfoundException extends RuntimeException {

  public ObjnotfoundException(String message) {
    super(message);
  }

  public ObjnotfoundException(String message, Throwable cause) {
    super(message, cause);
  }
}

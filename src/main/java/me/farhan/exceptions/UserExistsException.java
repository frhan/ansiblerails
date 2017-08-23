package me.farhan.exceptions;

public class UserExistsException extends Exception {

    public UserExistsException(final String msg){
      super(msg);
    }
}

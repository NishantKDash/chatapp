package com.nishant.exceptions;

public class WrongUserNameOrPasswordException extends Exception {
    public WrongUserNameOrPasswordException(String message)
    {
    	super(message);
    }
}

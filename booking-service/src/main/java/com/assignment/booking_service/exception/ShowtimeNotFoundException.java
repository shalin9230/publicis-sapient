package com.assignment.booking_service.exception;

public class ShowtimeNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -1704837251312475249L;

    public ShowtimeNotFoundException(String message) {super(message);}
}

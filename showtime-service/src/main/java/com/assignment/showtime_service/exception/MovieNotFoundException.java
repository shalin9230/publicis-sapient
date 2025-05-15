package com.assignment.showtime_service.exception;

public class MovieNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1771367868003042014L;

    public MovieNotFoundException(String message) {
        super(message);
    }
}

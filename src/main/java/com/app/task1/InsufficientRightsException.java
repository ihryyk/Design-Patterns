package com.app.task1;

public class InsufficientRightsException extends RuntimeException {

    public InsufficientRightsException(User user) {
        super("User " + user.name() + "has no rights to access this session.");
    }

}

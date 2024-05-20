package com.app.task1;

public class SessionManager {

    private final AccessChecker access = AccessChecker.getInstance();

    public Session createSession(User user, String levelLetter) {
        if (access.mayAccess(user, levelLetter)) {
            return new Session(user, levelLetter);
        } else {
            throw new InsufficientRightsException(user);
        }
    }

}

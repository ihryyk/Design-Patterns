package com.app.task1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionManagerTest {

    private SessionManager sut;

    @BeforeEach
    public void setup() {
        sut = new SessionManager();
    }

    @Test
    void givenUserWithAccess_whenCreateSession_thenSessionIsCreated() throws IOException {
        User user = new User("Ihor", Role.JUNIOR);
        Session session = sut.createSession(user, "A");
        assertNotNull(session);
    }

    @Test
    void givenUserWithoutAccess_whenCreateSession_thenExceptionIsThrown() {
        User user = new User("Ihor", Role.JUNIOR);
        assertThrows(InsufficientRightsException.class, () -> sut.createSession(user, "B"));
    }

}
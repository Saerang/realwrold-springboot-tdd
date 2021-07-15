package io.realworld.user.app.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(Long userId) {
        super("User " + userId + " already exist.");
    }
}

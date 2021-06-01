package com.codechallenge.rabbitpubsub.sub.exception;

public class DeliberateErrorForDLQTestException extends RuntimeException {
    public DeliberateErrorForDLQTestException(String message) {
        super(message);
    }
}

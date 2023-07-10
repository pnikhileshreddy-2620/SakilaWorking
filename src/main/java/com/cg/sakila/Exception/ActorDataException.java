package com.cg.sakila.Exception;

public class ActorDataException extends Exception {

    public ActorDataException() {
        super();
    }

    public ActorDataException(String message) {
        super(message);
    }

    public ActorDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActorDataException(Throwable cause) {
        super(cause);
    }
}

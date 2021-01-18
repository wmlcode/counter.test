package ru.sberbank.counter.exception;

public class CounterException extends Exception {

    public CounterException() {
        super();
    }

    public CounterException(String message) {
        super(message);
    }

    public CounterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CounterException(Throwable cause) {
        super(cause);
    }

    protected CounterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

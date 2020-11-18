package ru.ssau.tk.blashbanova.exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}

package ru.ssau.tk.blashbanova.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -8674914464360323396L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}

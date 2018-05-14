package com.blueskykong.javaspi.exception;

public class ObjectSerializerException extends Exception {
    private static final long serialVersionUID = -948934144333391208L;

    public ObjectSerializerException() {
    }

    public ObjectSerializerException(String message) {
        super(message);
    }

    public ObjectSerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectSerializerException(Throwable cause) {
        super(cause);
    }
}

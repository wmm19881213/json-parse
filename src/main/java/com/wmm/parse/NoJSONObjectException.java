package com.wmm.parse;

public class NoJSONObjectException extends  Exception {

    public NoJSONObjectException() {
    }

    public NoJSONObjectException(String message) {
        super(message);
    }

    public NoJSONObjectException(Throwable cause) {
        super(cause);
    }

    public NoJSONObjectException(String message, Throwable cause) {
        super(message, cause);
    }


    public NoJSONObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}

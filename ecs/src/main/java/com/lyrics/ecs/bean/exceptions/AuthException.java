package com.lyrics.ecs.bean.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends RuntimeException {
    private Integer code = 401;

    public AuthException() {
    }

    public AuthException(String message) {
        super("Auth Exception: " + message);
    }

    public AuthException(String message, Throwable cause) {
        super("Auth Exception" + message, cause);
    }
}
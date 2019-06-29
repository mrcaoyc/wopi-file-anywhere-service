package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;

/**
 * 身份无效异常
 *
 * @author Eastedu
 */
public class CredentialsInvalidException extends ForbiddenException {
    private static final long serialVersionUID = 1L;

    public CredentialsInvalidException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public CredentialsInvalidException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public CredentialsInvalidException(int code, String message) {
        super(code, message);
    }

    public CredentialsInvalidException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
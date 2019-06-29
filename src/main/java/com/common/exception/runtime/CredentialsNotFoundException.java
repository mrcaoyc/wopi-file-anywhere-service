package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;

/**
 * 未提供身份异常
 *
 * @author Eastedu
 */
public class CredentialsNotFoundException extends ForbiddenException {
    private static final long serialVersionUID = 1L;

    public CredentialsNotFoundException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public CredentialsNotFoundException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public CredentialsNotFoundException(int code, String message) {
        super(code, message);
    }

    public CredentialsNotFoundException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
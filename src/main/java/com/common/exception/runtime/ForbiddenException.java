package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;
import com.common.exception.BaseRuntimeException;

/**
 * 未授权异常
 *
 * @author Eastedu
 */
public class ForbiddenException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public ForbiddenException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public ForbiddenException(int code, String message) {
        super(code, message);
    }

    public ForbiddenException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
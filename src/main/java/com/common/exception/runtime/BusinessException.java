package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;
import com.common.exception.BaseRuntimeException;

/**
 * 业务异常
 *
 * @author Eastedu
 */
public class BusinessException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public BusinessException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}

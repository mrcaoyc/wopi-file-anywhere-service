package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;
import com.common.exception.BaseRuntimeException;

/**
 * 请求无效异常
 *
 * @author Eastedu
 */
public class BadRequestException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public BadRequestException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public BadRequestException(int code, String message) {
        super(code, message);
    }

    public BadRequestException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}

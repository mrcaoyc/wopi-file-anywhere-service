package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;
import com.common.exception.BaseRuntimeException;

/**
 * 远程服务不可用异常
 *
 * @author yuansongming
 */
public class ServiceUnavailableException extends BaseRuntimeException {
    public ServiceUnavailableException(BaseErrorMessage errorMessage) {
        super(errorMessage);
    }

    public ServiceUnavailableException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public ServiceUnavailableException(int code, String message) {
        super(code, message);
    }

    public ServiceUnavailableException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}

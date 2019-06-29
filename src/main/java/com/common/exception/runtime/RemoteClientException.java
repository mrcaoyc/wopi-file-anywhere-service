package com.common.exception.runtime;

import com.common.exception.BaseErrorMessage;
import com.common.exception.BaseRuntimeException;

/**
 * 远程调用异常，调用方必须处理，不允许直接抛给外层框架
 *
 * @author Eastedu
 */
public class RemoteClientException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer httpStatus;

    public RemoteClientException(BaseErrorMessage errorMessage, Integer httpStatus) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }

    public RemoteClientException(BaseErrorMessage errorMessage, Integer httpStatus, Throwable throwable) {
        super(errorMessage, throwable);
        this.httpStatus = httpStatus;
    }

    public RemoteClientException(int code, String message, Integer httpStatus) {
        super(code, message);
        this.httpStatus = httpStatus;
    }

    public RemoteClientException(int code, String message, Integer httpStatus, Throwable throwable) {
        super(code, message, throwable);
        this.httpStatus = httpStatus;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}

package com.common.exception;

import java.util.Objects;

/**
 * 运行时异常基类，调用方可选处理，如果不处理则由外层框架处理
 *
 * @author Eastedu
 */
public class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private BaseErrorMessage errorMessage;

    public BaseRuntimeException(BaseErrorMessage errorMessage) {
        if (Objects.nonNull(errorMessage)) {
            this.errorMessage = errorMessage;
            this.code = errorMessage.getCode();
            this.message = errorMessage.getMessage();
        }
    }

    public BaseRuntimeException(BaseErrorMessage errorMessage, Throwable throwable) {
        super(throwable);
        if (Objects.nonNull(errorMessage)) {
            this.errorMessage = errorMessage;
            this.code = errorMessage.getCode();
            this.message = errorMessage.getMessage();
        }
    }

    public BaseRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseErrorMessage getErrorMessage() {
        if (Objects.isNull(errorMessage)) {
            return new BaseErrorMessage() {
                @Override
                public int getCode() {
                    return code;
                }

                @Override
                public String getMessage() {
                    return message;
                }
            };
        } else {
            return errorMessage;
        }
    }
}
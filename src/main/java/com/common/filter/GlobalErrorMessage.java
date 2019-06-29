package com.common.filter;

import com.common.exception.BaseErrorMessage;

/**
 * @author Eastedu
 */

public enum GlobalErrorMessage implements BaseErrorMessage {
    /**
     *
     */
    ARGUMENT_VALID_ERROR(400, "参数验证错误"),
    ARGUMENT_BIND_ERROR(400, "参数绑定错误"),
    ARGUMENT_JSON_ERROR(400, "参数格式错误"),
    INTERNAL_SERVER_ERROR(500, "您的请求暂时无法处理！"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    METHOD_NOT_ALLOWED(405, "方法不被允许"),
    //
    ;

    private int code;
    private String message;

    GlobalErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
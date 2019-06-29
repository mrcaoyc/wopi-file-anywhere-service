package com.wopi.contants;

import com.common.exception.BaseErrorMessage;

/**
 * @author CaoYongCheng
 */
public enum FileErrorEnum implements BaseErrorMessage {
    /**
     * 文件不存在
     */
    NOT_FOUND(0, "文件不存在！");

    private int code;
    private String message;

    FileErrorEnum(int code, String message) {
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

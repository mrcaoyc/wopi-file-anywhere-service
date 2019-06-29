package com.common.exception;

/**
 * 所有异常信息接口类
 * 主要是为了实现error重用
 *
 * @author Eastedu
 */
public interface BaseErrorMessage {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    String getMessage();

    /**
     * 根据code和message直接创建异常消息对象
     *
     * @param code    错误码
     * @param message 错误消息
     * @return 异常信息
     */
    static BaseErrorMessage of(int code, String message) {
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
    }
}
package com.common.exception;

import lombok.Data;

/**
 * @author YuanSongMing
 */
@Data
public class ClientErrorMessage implements BaseErrorMessage {
    private int code;
    private String message;
    private int httpStatus;
}
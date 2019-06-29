package com.common.filter;

import com.common.exception.BaseRuntimeException;
import com.common.exception.ErrorMessage;
import com.common.exception.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 统一验证异常处理器
 *
 * @author YuanSongMing
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * 参数绑定错误，处理参数类型转换错误，比如将字符串赋值给数字型
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public HttpEntity<?> bindExceptionHandler(BindException e) {
        int index = 0;
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : fieldErrors) {
            if (!StringUtils.isEmpty(error.getDefaultMessage()) && !StringUtils.isEmpty(error.getField())) {
                if (index == 0) {
                    index++;
                } else {
                    errorMessage.append("\n");
                }
                errorMessage.append("请求参数类型错误,字段名:").append(error.getField()).append(",字段值:")
                        .append(error.getRejectedValue());
            }
        }
        LOGGER.debug("请求参数校验失败,异常信息:{}.", e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(GlobalErrorMessage.ARGUMENT_BIND_ERROR.getCode(), errorMessage.toString()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * 参数校验错误，处理参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        int index = 0;
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : fieldErrors) {
            if (index == 0) {
                index++;
            } else {
                errorMessage.append("\n");
            }
            if (!StringUtils.isEmpty(error.getDefaultMessage()) && !StringUtils.isEmpty(error.getField())) {
                errorMessage.append(error.getDefaultMessage());
            }
        }
        LOGGER.debug("请求参数绑定失败,异常信息:{}.", e.getMessage());
        return new ResponseEntity<Object>(new ErrorMessage(
                GlobalErrorMessage.ARGUMENT_VALID_ERROR.getCode(), errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求格式转换错误，请求内容不符合JSON格式
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public HttpEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.debug("请求格式转换错误,错误信息:{}", e.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorMessage(
                GlobalErrorMessage.ARGUMENT_JSON_ERROR.getCode(), "服务器无法识别请求"), HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求中提交的实体并不是服务器中所支持的格式
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public HttpEntity<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        LOGGER.debug("不支持的媒体类型,错误信息:{}", e.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorMessage(
                GlobalErrorMessage.UNSUPPORTED_MEDIA_TYPE.getCode(), "不支持的媒体类型"), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 方法不被允许
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public HttpEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.debug("方法不被允许,错误信息:{}", e.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorMessage(
                GlobalErrorMessage.METHOD_NOT_ALLOWED.getCode(), "方法不被允许"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 未授权异常，没有权限
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public HttpEntity<?> forbiddenExceptionHandler(Exception e) {
        ForbiddenException error = (ForbiddenException) e;
        LOGGER.debug("未授权异常,错误码:{},错误消息:{}.堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()), HttpStatus.FORBIDDEN);
    }

    /**
     * 禁止访问异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public HttpEntity<?> unauthorizedExceptionHandler(Exception e) {
        UnauthorizedException error = (UnauthorizedException) e;
        LOGGER.debug("禁止访问异常,错误码:{},错误消息:{}.堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 禁止访问异常，身份凭证错误或者无法解析
     */
    @ExceptionHandler(CredentialsInvalidException.class)
    @ResponseBody
    public HttpEntity<?> credentialsInvalidException(Exception e) {
        CredentialsInvalidException error = (CredentialsInvalidException) e;
        LOGGER.debug("禁止访问异常,错误码:{},错误消息:{}.堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 禁止访问异常，身份凭证未提供
     */
    @ExceptionHandler(CredentialsNotFoundException.class)
    @ResponseBody
    public HttpEntity<?> credentialsNotFoundException(Exception e) {
        CredentialsNotFoundException error = (CredentialsNotFoundException) e;
        LOGGER.debug("禁止访问异常,错误码:{},错误消息:{}.堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public HttpEntity<?> businessExceptionHandler(Exception e) {
        BusinessException error = (BusinessException) e;
        LOGGER.debug("业务异常,错误码:{},错误消息:{}.", error.getCode(), error.getMessage());
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 远程调用异常
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseBody
    public HttpEntity<?> serviceUnavailableException(Exception e) {
        ServiceUnavailableException error = (ServiceUnavailableException) e;
        LOGGER.debug("调用远程服务异常,错误码:{},错误消息:{},堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 未识别的运行基类异常
     */
    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseBody
    public HttpEntity<?> baseRuntimeException(Exception e) {
        BaseRuntimeException error = (BaseRuntimeException) e;
        LOGGER.debug("未识别的运行基类异常,错误码:{},错误消息:{},堆栈信息:{}.", error.getCode(), error.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), error.getMessage()),
                HttpStatus.BAD_REQUEST);
    }


    /**
     * 全局错误
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpEntity<?> exceptionHandler(Exception e) {
        LOGGER.error("系统内部错误,异常信息:{},堆栈信息:{}.", e.getMessage(), e);
        return new ResponseEntity<>(
                new ErrorMessage(GlobalErrorMessage.INTERNAL_SERVER_ERROR.getCode(), GlobalErrorMessage.INTERNAL_SERVER_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
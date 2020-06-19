package com.dili.account.exception.global;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dili.ss.domain.BaseOutput;

/**
 * @author: miaoguoxin
 * @date: 2020/4/8 11:22
 */
@ControllerAdvice
public class GenericGlobalExceptionResolver {
    @Autowired
    private Environment environment;
    /**
     * 系统模块标识code
     */
    private Integer systemCode;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericGlobalExceptionResolver.class);

    @PostConstruct
    public void init() {
        String appName = environment.getProperty("spring.application.name");
        SystemCodeMark systemCodeMark = SystemCodeMark.getSystemCode(appName);
        if (systemCodeMark == null) {
            this.systemCode = null;
        } else {
            this.systemCode = systemCodeMark.getCode();
        }
    }

//    /**
//     * 方法参数校验处理，配合validator
//     */
//    @ResponseBody
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public BaseOutput<?> handleMethodArgException(MethodArgumentNotValidException ex) {
//        return BaseOutput.failure(systemCode == null ?
//                        IErrorCode.ILLEGAL_PARAMS.getCode() : systemCode,
//                errorMessage(ex.getBindingResult()));
//    }
//
//    /**
//     * 参数断言异常处理
//     */
//    @ResponseBody
//    @ExceptionHandler({IllegalArgumentException.class})
//    public BaseOutput<?> handleIllegalArgsException(IllegalArgumentException ex) {
//        return BaseOutput.failure(ex.getMessage());
//    }
//
//    /**
//     * 单参数校验异常处理
//     */
//    @ResponseBody
//    @ExceptionHandler({ConstraintViolationException.class})
//    public Message<?> handleConstraintViolationException(ConstraintViolationException ex) {
//        return Message.failure(IErrorCode.ILLEGAL_PARAMS.getCode(), ex.getMessage());
//    }
//
//    /**
//     * 用于webflux的应用捕获参数校验
//     */
//    @ResponseBody
//    @ExceptionHandler({WebExchangeBindException.class})
//    public Message<?> handlerMethodArgBindException(WebExchangeBindException ex) {
//        return Message.failure(IErrorCode.ILLEGAL_PARAMS.getCode(),
//                errorMessage(ex.getBindingResult()));
//    }
//
//    /**
//     * 415处理
//     */
//    @ResponseBody
//    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
//    public Message<?> handle415Exception(HttpMediaTypeNotSupportedException ex) {
//        return Message.failure(415, "Unsupported Media Type");
//    }
//
//    /**
//     * 400处理
//     */
//    @ResponseBody
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public Message<?> handle400Exception(HttpMessageNotReadableException ex) {
//        LOGGER.error("参数有误：{}", ex.getMessage());
//        return Message.failure(400, "Bad Request");
//    }
//
//    /**
//     * 405处理
//     */
//    @ResponseBody
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    public Message<?> handle405Exception(HttpRequestMethodNotSupportedException ex) {
//        return Message.failure(405, "Method Not Allowed");
//    }
//
//    /**
//     * 基础业务异常处理
//     */
//    @ResponseBody
//    @ExceptionHandler({BaseBusinessException.class})
//    public Message<?> handlerBusinessException(BaseBusinessException e) {
//        return Message.failure(e.getCode(), e.getMessage());
//    }
//
//    /**
//     * 处理未自定义的异常
//     */
//    @ResponseBody
//    @ExceptionHandler({Exception.class})
//    public BaseOutput<?> handlerOtherException(Exception e) {
//        LOGGER.error("其他异常:{}", e);
//        return BaseOutput.failure(IErrorCode.UNKNOWN_ERROR.getName());
//    }

    private static String errorMessage(BindingResult result) {
        if (result == null) {
            return "";
        }
        List<ObjectError> allErrors = result.getAllErrors();
        return allErrors.stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("未知参数错误");
    }
}

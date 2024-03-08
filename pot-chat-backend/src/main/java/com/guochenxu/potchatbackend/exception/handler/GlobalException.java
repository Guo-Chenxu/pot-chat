package com.guochenxu.potchatbackend.exception.handler;

import com.guochenxu.potchatbackend.constants.HttpCode;
import com.guochenxu.potchatbackend.dto.R;
import com.guochenxu.potchatbackend.exception.IllegalException;
import com.guochenxu.potchatbackend.exception.NoUserException;
import com.guochenxu.potchatbackend.exception.NotFoundException;
import com.guochenxu.potchatbackend.exception.VisitLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * sa-token异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    // 拦截：用户不存在异常
    @ExceptionHandler(NoUserException.class)
    public R handlerException(NoUserException e) {
        log.error("用户不存在：", e);
        return R.error(HttpCode.NO_USER, e.getMessage());
    }

    // 拦截：指定参数不存在异常
    @ExceptionHandler(NotFoundException.class)
    public R handlerException(NotFoundException e) {
        log.error("指定参数不存在：", e);
        return R.error(HttpCode.NOT_FOUND, e.getMessage());
    }

    // 拦截：用户非法操作
    @ExceptionHandler(IllegalException.class)
    public R handlerException(IllegalException e) {
        log.error("用户非法操作：", e);
        return R.error(HttpCode.ILLEGAL_OPERATION, e.getMessage());
    }

    // 拦截：接口访问频繁异常
    @ExceptionHandler(VisitLimitException.class)
    public R handlerException(VisitLimitException e) {
        log.error("接口访问过于频繁：", e);
        return R.error(HttpCode.VISIT_LIMIT, e.getMessage());
    }

    // 拦截：空指针异常
    @ExceptionHandler(NullPointerException.class)
    public R handlerException(NullPointerException e) {
        log.error("出现空指针异常: ", e);
        return R.error(e.getMessage());
    }

    // 拦截：其它所有异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handlerException(MissingServletRequestParameterException e) {
        log.error("缺少参数异常: ", e);
        return new R<>(HttpCode.REQUEST_PARAM_BLANK, e.getMessage(), null);
    }

    // 拦截：其它所有异常
    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e) {
        log.error("出现异常: ", e);
        return R.error(e.getMessage());
    }
}
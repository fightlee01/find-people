package com.lee.exceptionhandler;

import com.lee.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error() {
        return R.error().message("内部服务错误！！！");
    }

    @ExceptionHandler(FindPeopleException.class)
    @ResponseBody
    public R myException(FindPeopleException e) {
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}

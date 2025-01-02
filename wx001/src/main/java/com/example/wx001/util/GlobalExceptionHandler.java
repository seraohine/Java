package com.example.wx001.util;

import com.example.wx001.domain.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理类，监控运行中的所有类，一旦发现异常，自动向指定的方法向前端返回提示信息
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public R handleNullPointerException(NullPointerException ex) {
        return new R(500, "空指针异常", null);
    }
    @ExceptionHandler(BusinessException.class)
    public R handleBusinessException(BusinessException ex) {
        //System.out.println("业务异常：" + ex.getMessage());
        return new R(500, ex.getMessage(), null);
    }

}
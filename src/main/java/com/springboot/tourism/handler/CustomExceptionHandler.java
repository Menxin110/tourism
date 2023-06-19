package com.springboot.tourism.handler;

import com.springboot.tourism.util.JsonData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author oneda
 * @version 菜鸟
 */


@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 异常处理方法
     * 解决表中索引唯一引起的异常
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public JsonData exceptionHandler(SQLIntegrityConstraintViolationException ex){
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            jsonData.setMsg(msg);
            return jsonData;
        }

        jsonData.setMsg("未知错误");
        return jsonData;
    }
}

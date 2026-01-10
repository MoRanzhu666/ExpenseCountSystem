package com.lyrics.ecs.handler;

import com.lyrics.ecs.bean.exceptions.AuthException;
import com.lyrics.ecs.bean.exceptions.BadRequestException;
import com.lyrics.ecs.bean.po.ResultPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 全局异常处理器
 * 统一捕获项目中所有异常，返回标准化的ResultPo格式
 */
@Slf4j
@ControllerAdvice  // 全局异常处理注解，作用于所有@Controller注解的类
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常（已知异常）
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody  // 返回JSON格式
    public ResultPo<Void> handleBusinessException(BadRequestException e) {
        // 打印异常日志（级别为warn，因为是已知业务异常）
        log.warn("业务异常：{}", e.getMessage());
        // 返回异常信息（使用自定义状态码和消息）
        return ResultPo.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody  // 返回JSON格式
    public ResultPo<Void> handleAuthException(AuthException e) {
        // 打印异常日志（级别为warn，因为是已知业务异常）
        log.warn("业务异常：{}", e.getMessage());
        // 返回异常信息（使用自定义状态码和消息）
        return ResultPo.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常（如@Valid注解的参数校验失败）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultPo<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 提取参数校验失败的详细信息
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder("参数校验失败：");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMsg.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append("；");
        }
        // 打印异常日志
        log.warn(errorMsg.toString());
        // 返回参数错误信息（状态码400）
        return ResultPo.error(400, errorMsg.toString());
    }

    /**
     * 处理数据库异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResultPo<Void> handleSQLException(SQLException e) {
        // 数据库异常可能包含敏感信息，日志打印完整堆栈，返回给前端简化信息
        log.error("数据库异常", e);
        return ResultPo.error(500, "数据库操作失败，请联系管理员，"+e.getMessage());
    }

    /**
     * 处理所有未捕获的异常（未知异常）
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultPo<Void> handleException(Exception e) {
        // 未知异常打印完整堆栈，便于排查
        log.error("系统异常", e);
        // 返回通用错误信息，避免暴露敏感信息
        return ResultPo.error(500, "系统繁忙，请稍后再试，"+e.getMessage());
    }
}
    
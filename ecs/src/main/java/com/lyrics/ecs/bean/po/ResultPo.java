package com.lyrics.ecs.bean.po;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用结果返回类
 * 用于统一封装API接口的返回数据格式
 * @param <T> 数据部分的类型
 */
@Data
public class ResultPo<T> implements Serializable {

    /**
     * 状态码：200表示成功，非200表示异常
     */
    private Integer code;

    /**
     * 返回消息：成功时一般为"success"，异常时为错误描述
     */
    private String message;

    /**
     * 业务数据：成功时返回具体数据，异常时可为null
     */
    private T data;

    /**
     * 响应时间戳
     */
    private LocalDateTime timestamp;

    // 构造方法私有化，通过静态方法创建实例
    private ResultPo() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 成功返回（无数据）
     */
    public static <T> ResultPo<T> success() {
        ResultPo<T> result = new ResultPo<>();
        result.setCode(200);
        return result;
    }

    /**
     * 成功返回（信息）
     */
    public static <T> ResultPo<T> success(String message) {
        ResultPo<T> result = new ResultPo<>();
        result.setMessage(message);
        result.setCode(200);
        return result;
    }

    /**
     * 成功返回（带数据）
     * @param data 业务数据
     */
    public static <T> ResultPo<T> success(T data) {
        ResultPo<T> result = success();
        result.setData(data);
        return result;
    }

    /**
     * 成功返回（带数据和自定义消息）
     * @param data 业务数据
     * @param message 自定义消息
     */
    public static <T> ResultPo<T> success(T data, String message) {
        ResultPo<T> result = success(data);
        result.setMessage(message);
        return result;
    }

    /**
     * 异常返回（默认消息）
     */
    public static <T> ResultPo<T> error() {
        ResultPo<T> result = new ResultPo<>();
        result.setCode(500);
        result.setMessage("操作失败");
        return result;
    }

    /**
     * 异常返回（自定义消息）
     * @param message 错误描述
     */
    public static <T> ResultPo<T> error(String message) {
        ResultPo<T> result = error();
        result.setMessage(message);
        return result;
    }

    /**
     * 异常返回（自定义状态码和消息）
     * @param code 状态码
     * @param message 错误描述
     */
    public static <T> ResultPo<T> error(Integer code, String message) {
        ResultPo<T> result = new ResultPo<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}

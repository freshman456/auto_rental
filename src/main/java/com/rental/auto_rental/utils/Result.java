package com.rental.auto_rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private boolean success;
    private T data;

    private Result() {

    }

    /**
     * 创建一个表示操作成功的Result对象。
     *
     * @param <T> 结果对象的类型。
     * @return 返回一个初始化为成功状态的Result对象，包含成功标志、成功代码和成功消息。
     */
    public static <T> Result<T> success() {
        return new Result<T>().setSuccess(true).setCode(ResultCode.SUCCESS).setMessage("操作成功");
    }

    /**
     * 创建一个表示操作成功的 Result 对象。
     *
     * @param data 成功时返回的数据。
     * @param <T>  数据的类型。
     * @return 返回一个初始化为成功状态的 Result 对象，包含指定的数据。
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>().setSuccess(true).
                setCode(ResultCode.SUCCESS)
                .setMessage("操作成功").setData(data);
    }

    /**
     * 创建一个表示操作失败的结果对象。
     *
     * @param <T> 结果对象的类型。
     * @return 返回一个初始化为失败状态的 Result 对象，其中包含错误代码和消息。
     */
    public static <T> Result<T> fail() {
        return new Result<T>().setSuccess(false)
                .setCode(ResultCode.ERROR)
                .setMessage("操作失败");
    }

}

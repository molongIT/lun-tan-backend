package com.pxl.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果封装类
 */
@Data
@NoArgsConstructor
public class ResultWrapper<T> {
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "success";

    private int code;
    private String msg;
    private T data;

    public ResultWrapper(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultWrapper(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultWrapper<T> success() {
        return new ResultWrapper<T>(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return new ResultWrapper<T>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResultWrapper<T> fail(int code, String msg) {
        return new ResultWrapper<T>(code, msg);
    }

    public static <T> ResultWrapper<T> fail(ResultCodeEnum resultCodeEnum) {
        return fail(resultCodeEnum.getCode(), resultCodeEnum.getValue());
    }
}

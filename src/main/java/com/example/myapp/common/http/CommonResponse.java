package com.example.myapp.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    /**
     * 消息状态码
     */
    private int code;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息对象
     */
    private Object data;

    // 操作成功
    public static CommonResponse success(String msg, Object data) {
        return new CommonResponse(HttpStatus.SUCCESS, msg, data);
    }

    public static CommonResponse success(Object data) {
        return CommonResponse.success("操作成功", data);
    }

    public static CommonResponse success(String msg) {
        return CommonResponse.success(msg, null);
    }

    public static CommonResponse success() {
        return CommonResponse.success("操作成功");
    }

    // 操作失败
    public static CommonResponse error(int code, String msg) {
        return new CommonResponse(code, msg, null);
    }

    public static CommonResponse error(String msg) {
        return CommonResponse.error(HttpStatus.ERROR, msg);
    }

    public static CommonResponse error() {
        return CommonResponse.error("操作失败");
    }
}

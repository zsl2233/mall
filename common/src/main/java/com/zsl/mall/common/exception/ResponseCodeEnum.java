package com.zsl.mall.common.exception;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {

    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"参数格式校验失败");

    private int code;
    private String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

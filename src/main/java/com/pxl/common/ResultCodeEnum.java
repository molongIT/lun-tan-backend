package com.pxl.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    ERROR_UNKNOWN_EXCEPTION(2000, "系统未知异常"),
    ERROR_PARAMETER_VALIDATE(2002, "参数校验异常"),
    ERROR_RPC(2003, "remote invoke error"),
    ERROR_RECORD_NOT_EXIST(2004,"record not exist"),
    ERROR_DUPLICATE_OPERATION(2005,"duplicate operation"),
    /**
     * 用户校验 3000-3100
     */
    ERROR_USERNAME_OR_PASSWORD(3000, "账户名或密码错误"),
    ERROR_DUPLICATE_TAG_NAME(3100, "该名称的标签已存在"),
    ERROR_TAG_NOT_EXIST(3101, "标签不存在"),
    ERROR_TOKEN(3103, "无效的token"),
    ERROR_USER_NOT_EXIST(3104,"用户不存在"),
    ERROR_CODE_NOT_MATCH(9999, "错误码未匹配到"),

    /**
     * AlipaySUb
     */
    ERROR_SIGN(3200, "签约异常"),
    ERROR_CIRCLE_DEDUCT(3201,"周期扣款失败"),
    ERROR_SUBBING(3202,"用户正在订阅中"),
    ERROR_SUB_MOUTH_FOR_YEAR(3203,"年用户不能再按月订阅"),
    NOT_ACTIVE(3204,"目前没有生效"),
    INVALID_SUB(3205,"过期无效的签约"),
    ERROR_UPGRADE(3206,"不允许升级"),
    ERROR_CANCEL_SUBSCRIPTION(3207,"取消订阅异常"),

    /**
     * hutool网络异常
     */
    ERROR_NETWORK(3301,"network error"),

    /**
     * AirwallexSub相关
     */
    ERROR_DISABLE_ANOTHER_SUB(3311,"discardAnotherTypeSub error"),
    INVALID_AIRWALLEX_SUB(3205,"invalid sub");



    private final int code;
    private final String value;

    public static String getValueByCode(int code) {
        return Arrays.stream(values()).filter(f -> f.getCode() == code).findFirst().orElse(ERROR_CODE_NOT_MATCH).getValue();
    }
}

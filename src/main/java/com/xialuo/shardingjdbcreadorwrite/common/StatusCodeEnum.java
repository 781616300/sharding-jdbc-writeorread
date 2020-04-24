package com.xialuo.shardingjdbcreadorwrite.common;

import java.util.HashMap;
import java.util.Map;

public enum StatusCodeEnum {
  /** 1-999 系统通用. */
  SUCCESS(1, "成功"),
  SYSTEM_ERR(-1, "系统异常"),
  INVALID_TOKEN(0, "token error!"),

  ILLEGAL_REQUEST(2, "非法请求!"),
  PARAMETER_PARSING_FAILED(3, "参数解析失败!"),
  PARAMETER_VALID_FAILED(4, "参数验证失败!"),
  HTTP_MEDIA_TYPE_NOT_SUPPORTED(5, "HttpMedia类型不支持!"),
  UNAUTHENTICATED(6, "用户未认证"),
  UNAUTHORIZED(7, "当前操作未授权"),
  PARAMETER_TYPE_CONVERSION_FAILED(8, "参数类型转换异常!"),
  NOT_FOUND(404, "Not Found"),
  CODE_REPEAT(1001, "编码已存在"),
  NO_LOGIN(302, "未登录 需跳转"),
  LOGIN_SUCCESS(302, "登录成功 跳转目标页面"),
  LOGIN_OUT(302, "登出成功 跳转目标页面"),
  SIGN_NO(10, "验签失败"),
  APPID_NO(11, "APPID 缺失"),


  ;

  private static final Map<Integer, StatusCodeEnum> stringToEnum = new HashMap<>();

  static {
    for (StatusCodeEnum statusCode : values()) {
      stringToEnum.put(statusCode.code, statusCode);
    }
  }

  private final int code;
  private final String desc;

  StatusCodeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static StatusCodeEnum fromCode(Integer code) {
    return stringToEnum.get(code);
  }

  public Integer getCode() {
    return this.code;
  }

  public String getDesc() {
    return this.desc;
  }
}

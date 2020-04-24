package com.xialuo.shardingjdbcreadorwrite.common;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResultDto<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer code;

  private String message;

  private T data;

  /**
   * .
   *
   * @param code 状态码
   * @param msg 描述
   */
  public ResultDto(Integer code, String msg) {

    setMessage(msg);
    setCode(code);
  }

  /**
   * .
   *
   * @param sc 状态码枚举
   * @return
   */
  public ResultDto(StatusCodeEnum sc) {
    code = sc.getCode();
    setMessage(sc.getDesc());
  }


  /**
   * .
   *
   * @param sc 状态枚举
   * @param data 数据
   * @return
   */
  public ResultDto(StatusCodeEnum sc, T data) {
    code = sc.getCode();
    setMessage(sc.getDesc());
    this.data = data;
  }

  /**
   * .
   *
   * @param data 数据
   */
  public ResultDto(T data) {
    code = StatusCodeEnum.SUCCESS.getCode();
    setMessage(StatusCodeEnum.fromCode(code).getDesc());
    this.data = data;
  }

  public static <T> ResultDto<T> error() {
    return new ResultDto<>(StatusCodeEnum.SYSTEM_ERR);
  }

  public static <T> ResultDto<T> error(StatusCodeEnum statusCode) {
    return new ResultDto<>(statusCode);
  }

  public static <T> ResultDto<T> success() {
    return new ResultDto<>(StatusCodeEnum.SUCCESS);
  }

  public static <T> ResultDto<T> success(T data) {
    return new ResultDto<>(StatusCodeEnum.SUCCESS, data);
  }

  public static <T> ResultDto<T> buiss(StatusCodeEnum statusCode, T data) {
    return new ResultDto<>(statusCode, data);
  }

  public ResultDto() {
    super();
    // TODO Auto-generated constructor stub
  }

}

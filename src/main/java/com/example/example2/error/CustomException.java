package com.example.example2.error;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private String errCode;
  private String errMsg;

  public CustomException(String errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

}

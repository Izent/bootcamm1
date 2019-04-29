package com.example.example2.error;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidateException extends RuntimeException {

  private List<String> errMsg;

  public ValidateException(List<String> errMsg) {
    this.errMsg = errMsg;
  }
}

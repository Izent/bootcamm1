package com.example.example2.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

  @ExceptionHandler(CustomException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public String handleMyException(CustomException ex) {

    //log.info("Error!! : " + ex);

    return "Error: " + ex.getErrCode() + " Message: " + ex.getErrMsg();
  }

  @ExceptionHandler(ValidateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public List<String> handleMyException2(ValidateException ex) {

    // log.info("Error de validacion!! : " + ex);

    return ex.getErrMsg();
  }

}

package com.example.demo.handler;

import com.example.demo.exception.CommonException;
import com.example.demo.utils.style.JSONResult;
import com.example.demo.utils.StatusUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jack_YD
 * @create 2019/8/27 16:07
 */
@ControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(value = {CommonException.class})
  @ResponseBody
  public JSONResult commonException(CommonException e) {
    return new JSONResult( e.getCode(),e.getMsg());
  }


  @ExceptionHandler(value = {Exception.class})
  @ResponseBody
  public JSONResult exception(Exception e) {
    e.printStackTrace();
    return new JSONResult( StatusUtil.ERROR,null,"程序错误");
  }

}

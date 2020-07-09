package com.example.demo.utils;

/**
 * @author 花落泪知雨
 * @create 2019/8/26
 */
public class StatusUtil {
  public static final int OK = 200;//成功
  public static final int ERROR = 500;//程序未知错误

  public static final int NO_LOGIN=3000;
  public static final int LOGIN_ERROR = 3001;//登陆状态异常
  public static final int ACCESS_ERROR = 3002;//权限不足
  public static final int REPETITIVE_OPERATION = 3003;//重复操作

  public static final int PARAM_ERROR=4000;//参数错误

  public static final int TOKEN_ERROR =6000;//token错误
  public static final int TOKEN_TIMEOUT=6001;//toke超时



}

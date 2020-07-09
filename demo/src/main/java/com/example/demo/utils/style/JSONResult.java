package com.example.demo.utils.style;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author PC20
 * @create 2019/8/5
 */
@Getter @Setter
public class JSONResult implements Serializable {
    private static final long serialVersionUID = -4800793124936904868L;

    /**
     * 返回是否成功的状态码,200表示成功,
     * 201或其他值 表示失败
     */
    private Integer code;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String massage;

    public JSONResult(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.massage = msg;
    }
    public JSONResult(Integer code, String msg) {
        this.code = code;
        this.massage = msg;
    }

}

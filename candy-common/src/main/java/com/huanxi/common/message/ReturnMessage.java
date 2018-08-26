package com.huanxi.common.message;

import java.io.Serializable;

public class ReturnMessage implements Serializable {
    private static final long serialVersionUID = -3307660546347616895L;
    private int status;//状态码
    private String msg; //返回信息

    public int getStatus() {
        return status;
    }

    public ReturnMessage() {
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReturnMessage(int status, String msg) {
        this.status = status;
        this.msg = msg;

    }
}

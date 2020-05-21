package com.xinhai.demo01.bean;


import java.io.Serializable;

public class RespResult implements Serializable {
    private Integer status;
    private String msg;
    private Integer total;
    private Object data;

    public RespResult() {
    }

    public RespResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public RespResult(Integer status, String msg, Integer total, Object data) {
        this.status = status;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public static RespResult success(String msg){
        return new RespResult(200,msg);
    }
    public static RespResult success(String msg, Integer total,Object data){
        return new RespResult(200,msg,total,data);
    }

    public static RespResult error(String msg){
        return new RespResult(400,msg);
    }
    public static RespResult error(String msg, Integer total,Object data){
        return new RespResult(400,msg,total,data);
    }

    public Integer getStatus() {
        return status;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.xinhai.demo01.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseVO {

    //返回状态码
    private Integer code = 200;
    //总记录数量
    private Integer totals;
    //数据内容
    private List<?> list;

    @Override
    public String toString() {
        return "data{" +
                "code=" + code +
                ", totals=" + totals +
                ", list=" + list +
                '}';
    }


}

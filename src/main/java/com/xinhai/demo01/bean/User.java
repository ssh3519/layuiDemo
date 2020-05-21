package com.xinhai.demo01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-30 12:55:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -35624130743612962L;

    private Integer id;

    private String username;

    private Integer age;

    private Integer gender;

    private String email;
}
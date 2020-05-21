package com.xinhai.demo01.service;

import com.xinhai.demo01.bean.User;

import java.util.List;

public interface UserService{
    User selectById(Integer id);

    List<User> selectAll();

    Boolean insert(User user);

    Boolean update(User user);

    Boolean delete(Integer id);

    Boolean batchDelete(List<Integer> ids);
}

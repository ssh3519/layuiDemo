package com.xinhai.demo01.service.impl;

import com.xinhai.demo01.bean.User;
import com.xinhai.demo01.mapper.UserMapper;
import com.xinhai.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public Boolean insert(User user) {
        return userMapper.insert(user)==1;
    }

    @Override
    public Boolean update(User user) {
        return userMapper.update(user)==1;
    }

    @Override
    public Boolean delete(Integer id) {
        return userMapper.delete(id)==1;
    }

    @Override
    public Boolean batchDelete(List<Integer> ids) {
        return userMapper.batchDelete(ids)==ids.size();
    }
}

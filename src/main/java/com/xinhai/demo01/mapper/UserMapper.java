package com.xinhai.demo01.mapper;

import com.xinhai.demo01.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectById(Integer id);

    List<User> selectAll();

    Integer insert(User user);

    Integer update(User user);

    Integer delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);
}

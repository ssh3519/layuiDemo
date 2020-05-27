package com.xinhai.demo01.mapper;

import com.xinhai.demo01.bean.Score;

/**
 * @author: ssh
 * @email:
 * @Date: 2020/5/27 10:16
 */
public interface ScoreMapper {
    Score selectById(Integer id);
    Score selectByIdLock(Integer id);
    void updateScore(Score score);
}

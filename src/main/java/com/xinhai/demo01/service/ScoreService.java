package com.xinhai.demo01.service;

import com.xinhai.demo01.bean.Score;

/**
 * @author: ssh
 * @email:
 * @Date: 2020/5/27 10:16
 */
public interface ScoreService {
    Score selectById(Integer id);
    void updateScoreA();
    void updateScoreB();
}

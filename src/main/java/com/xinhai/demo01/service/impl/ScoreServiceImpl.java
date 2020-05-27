package com.xinhai.demo01.service.impl;

import com.xinhai.demo01.bean.Score;
import com.xinhai.demo01.mapper.ScoreMapper;
import com.xinhai.demo01.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 排他锁不能与其他锁共存
 * 一个事务获取了某行的排他锁，其他事务就不能再获取该行的锁
 * 获取排他锁的当前事务内可以对数据进行读取和修改
 * 不开启事务，FOR UPDATE 不会锁数据
 * FOR UPDATE 是写锁，读操作不会锁住
 * FOR UPDATE 即可能是行锁也可能是表锁
 * @author: ssh
 * @email:
 * @Date: 2020/5/27 10:16
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public Score selectById(Integer id) {
        return scoreMapper.selectById(id);
    }

    @Override
    public void updateScoreA() {
        Score score = scoreMapper.selectByIdLock(1);
        score.setScore(score.getScore()+10);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scoreMapper.updateScore(score);
    }

    @Override
    public void updateScoreB() {
//        Score score = scoreMapper.selectById(1);
        Score score = scoreMapper.selectByIdLock(1);
        score.setScore(score.getScore()+5);
        scoreMapper.updateScore(score);
    }
}

package com.xinhai.demo01.controller;

import com.xinhai.demo01.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ssh
 * @email:
 * @Date: 2020/5/27 10:22
 */
@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping(value = "/A")
    public String A(){
        scoreService.updateScoreA();
        return "success";
    }

    @GetMapping(value = "/B")
    public String B(){
        scoreService.updateScoreB();
        return "success";
    }
}

package com.xinhai.demo01.controller;

import com.xinhai.demo01.bean.Demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: ssh
 * @email: ssh@x.xinhai.com
 * @Date: 2020/4/16 12:46
 */
@RestController
public class DemoController {
    @GetMapping("/timeTest")
    public Demo timeTest() {
        Demo demo = new Demo();
        demo.setCreateTime(LocalDateTime.now());
        demo.setUpdateTime(new Date());
        return demo;
    }
}

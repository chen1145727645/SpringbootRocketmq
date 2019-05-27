package com.suoron.springboot.controller;

import com.suoron.springboot.service.ProducerService;
import com.suoron.springboot.service.impl.ProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController{

    @Autowired
    private ProducerService producerService;


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        producerService.send("TopicTest1", "1901", "通知!!!!");
        return "Hello Springboot!!!";
    }
}


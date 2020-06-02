package com.springboot.rabbitmq.controller;


import com.springboot.rabbitmq.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutControiller {

    @Autowired
    private FanoutProducer fanoutProducer;

    @RequestMapping("/sendMsg")
    public String sendMag(String queueName){
        fanoutProducer.send(queueName);
        return "ok";
    }


}

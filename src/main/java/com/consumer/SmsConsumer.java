package com.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者定义
 * @author tianyingke
 */

@Component
//放入队列
@RabbitListener(queues = "work_sms")
public class SmsConsumer {

    @RabbitHandler
    public void oricess(String msg){
        System.out.println("邮件发送的消息是: "+ msg);
    }


}

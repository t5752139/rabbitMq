package com.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者定义
 * @author tianyingke
 */

@Component
@RabbitListener(queues = "work_email")
public class EmaliConsumer {

    @RabbitHandler
    public void oricess(String msg){
        System.out.println("邮件发送的消息是: "+ msg);
    }


}

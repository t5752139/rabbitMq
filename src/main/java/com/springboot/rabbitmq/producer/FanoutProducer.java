package com.springboot.rabbitmq.producer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @author  tianyingke
 */
@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     *
     * @param queueName 队列名称
     */
    public void send(String queueName){

            System.out.println("队列名字"+ queueName);
            String mas = ""+new Date().getTime();
            //发送消息
            amqpTemplate.convertAndSend(queueName,mas);
        }
}

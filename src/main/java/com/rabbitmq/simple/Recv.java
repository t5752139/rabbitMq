package com.rabbitmq.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 获取消息
 */
public class Recv {
    private static  final  String QUEUE = "tets_name";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
       //获取工厂
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE,true,queueingConsumer);
        while (true){
                //获取消息 的 包装提
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String s = new String(delivery.getBody());
            System.out.println("接受消息: "+s);

        }


    }
}

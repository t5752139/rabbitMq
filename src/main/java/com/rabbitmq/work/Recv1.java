package com.rabbitmq.work;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 接受消息1
 *
 */
public class Recv1 {
    private static  final  String QUEUE = "tets_work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE,false,false,false,null);
        //定义消费信息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("接受消息 : " + s);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //监听队列
        /**
         * 参数1 消息那个队列的队列名称 队列名称
         * 参数2 开始消息自动确认机制
         * 参数3 消费时回调接口
         */
        channel.basicConsume(QUEUE,true,defaultConsumer);




    }

}

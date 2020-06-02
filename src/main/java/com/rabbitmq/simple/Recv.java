package com.rabbitmq.simple;


import com.rabbitmq.client.*;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 获取消息
 */
public class Recv {
    private static  final String QUEUE = "tets_name";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        /**
         * 新api
         */
        //获取工厂
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE,false,false,false,null);
        /**
         * 定义消费者
         * 如果有消息就会调用 这个方法
         *  时间模型
          */
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("接受消息: "+ s);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE,true,defaultConsumer);
    }

    /**
     * 老api
     */
    public static void oldpiu() throws IOException, TimeoutException, InterruptedException {
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

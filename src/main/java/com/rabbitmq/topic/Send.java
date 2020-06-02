package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static  final  String exchange = "tets_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnertionUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建交换机
        /**
         * topic 通配符  #代表匹配一个或多个
         *              *代表匹配一个
         */
        channel.exchangeDeclare(exchange,"topic");
        //定义消息
        String msg = "hello java  mq";
        channel.basicPublish(exchange,"123.add",null,msg.getBytes());
        channel.close();
        connection.close();




    }
}

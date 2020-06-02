package com.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通过rouding  指定的key发送消息
 */
public class Send {
    private static  final  String exchange = "tets_exchange_rouding";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnertionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机为类型为 direct类型
        channel.exchangeDeclare(exchange,"direct");
        //定义消息
        String msg = "你好";
        String routingKey = "waring";
        channel.basicPublish(exchange,routingKey,null,msg.getBytes());
        //关闭流
        channel.close();
        connection.close();



    }
}

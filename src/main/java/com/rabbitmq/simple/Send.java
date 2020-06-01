package com.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

/**
 * 生产消息
 */
public class Send {

    private static  final  String QUEUE = "tets_name";
    public static void main(String[] args) throws IOException, TimeoutException {



       //获取工厂连接
        Connection connection = ConnertionUtils.getConnection();
        //从连接里面获取一个通道
        Channel channel = connection.createChannel();
        //创建声明队列
        channel.queueDeclare(QUEUE,false,false,false,null);
        //定一个消息
        String msg = "hello word";
        channel.basicPublish("",QUEUE,null,msg.getBytes());

        //关闭通道
        channel.close();
        connection.close();

    }

}

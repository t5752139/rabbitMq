package com.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者消息  多个接受者
 *                  k1
 * p  - -- queue                消费者接受消息是每一个一条,跟处理时间没关系,用的是轮询方式
 *                 k2
 */
public class Send {
    private static  final  String QUEUE = "tets_work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE,false,false,false,null);
        for (int i = 0; i <50 ; i++) {
            //消息内容
            String s= "rabbitMq "+i;
            //发送消息
            channel.basicPublish("",QUEUE,null,s.getBytes());
        }

        //关闭流
        channel.close();
        connection.close();
    }


}

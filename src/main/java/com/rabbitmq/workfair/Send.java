package com.rabbitmq.workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者消息  多个接受者
 *                  k1
 * p  - -- queue                公平分发,谁有处理多谁就收到多
 *                 k2
 */


public class Send {
    private static  final  String QUEUE = "tets_work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //保证每次执法送一次, 每次分发只有一个,收到回复继续发送
        //设置只发送一个
        channel.basicQos(1);
        //创建队列
        //第二个参数改为true持久化到硬盘上 false的话只会在内存中,
        //这样rabbitmq挂掉消息也不会丢失
        //小细节:创建好的队列不能被更改
        channel.queueDeclare(QUEUE,true,false,false,null);
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

package com.rabbitmq.ps;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 交换机exchange
 */
public class Send {
    private static  final  String exchange = "tets_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnertionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        /**
         * 交换机类型 fanout : 不处理路由建  只要有消息都能发送到队列里面
         *
         */
        channel.exchangeDeclare(exchange,"fanout");//分发
        //发送消息
        String msg="hello java";
        channel.basicPublish(exchange,"",null,msg.getBytes());

        channel.close();
        connection.close();


    }

}

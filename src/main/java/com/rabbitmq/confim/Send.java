package com.rabbitmq.confim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * confim模式   最大的好处在于异步    串行模式
 *  如果队列已经声明了事务模式,就不能再改成confim模式
 */
public class Send {
    private static final String QUEUE = "tets_name_confim";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取工厂连接
        Connection connection = ConnertionUtils.getConnection();
        //从连接里面获取一个通道
        Channel channel = connection.createChannel();
        //创建声明队列
        channel.queueDeclare(QUEUE, false, false, false, null);

        //开启confim模式
        channel.confirmSelect();

        //定一个消息
        String msg = "hello word";

        channel.basicPublish("", QUEUE, null, msg.getBytes());
        //判断是否成功
        if(channel.waitForConfirms()){
            System.out.println("msgens mas  ok");
        }else {
            System.out.println("msg   fianl");
        }
        channel.close();
        connection.close();


    }


}

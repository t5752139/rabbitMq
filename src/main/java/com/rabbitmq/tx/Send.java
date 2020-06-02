package com.rabbitmq.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnertionUtils;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产消息
 */
public class Send {

    private static final String QUEUE = "tets_name_1";

    public static void main(String[] args) throws IOException, TimeoutException {


        //获取工厂连接
        Connection connection = ConnertionUtils.getConnection();
        //从连接里面获取一个通道
        Channel channel = connection.createChannel();
        //创建声明队列
        channel.queueDeclare(QUEUE, false, false, false, null);
        //定一个消息
        String msg = "hello word";
        /**
         * 定义事务机制, 异常就回滚
         *  如果回滚 消费者就收不到消息
         */
        try {
            //开启事务
            channel.txSelect();
            channel.basicPublish("", QUEUE, null, msg.getBytes());
            int a = 1/0;
            //提交事务
            channel.txCommit();
        } catch (IOException e) {
            System.out.println("异常回滚");
            //回滚事务
            channel.txRollback();


        } finally {
            //关闭通道
            channel.close();
            connection.close();
        }


    }

}

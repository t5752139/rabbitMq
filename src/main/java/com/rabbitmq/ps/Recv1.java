package com.rabbitmq.ps;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 接受消息1
 *
 */
public class Recv1 {
    private static  final  String QUEUE = "tets_work_eml";
    private static  final  String exchange = "tets_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        final Channel channel = connection.createChannel();
        //创建队列
        //第二个参数改为true持久化到硬盘上 false的话只会在内存中,
        //这样rabbitmq挂掉消息也不会丢失
        channel.queueDeclare(QUEUE,false,false,false,null);
        //绑定队列到交换机上
        channel.queueBind(QUEUE,exchange,"");


        channel.basicQos(1);//每次分发之分发一个
        //定义消费信息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("接受消息 : " + s);
                //手动应答
                channel.basicAck(envelope.getDeliveryTag(),false);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //监听队列
        //false 代表手动应答   返回给生产者 收到之后 会在内存中删除
        //true  (自动确认模式) 一旦rabbitmq将消息分发给消费者就会从消息中删除
        channel.basicConsume(QUEUE,false,defaultConsumer);




    }

}

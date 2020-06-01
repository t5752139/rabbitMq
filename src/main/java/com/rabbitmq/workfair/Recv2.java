package com.rabbitmq.workfair;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 接受消息2
 *
 */
public class Recv2 {
    private static  final  String QUEUE = "tets_work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnertionUtils.getConnection();
        //获取通道
        final Channel channel = connection.createChannel();
        //创建队列
        //第二个参数改为true持久化到硬盘上 false的话只会在内存中,
        //这样rabbitmq挂掉消息也不会丢失
        channel.queueDeclare(QUEUE,true,false,false,null);
        channel.basicQos(1);//保证每次分发只有一个
        //定义消费信息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("接受消息 : " + s);
                //手动应答
                channel.basicAck(envelope.getDeliveryTag(),false);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //监听队列
        //fasle  改成手动应答
        channel.basicConsume(QUEUE,false,defaultConsumer);




    }

}

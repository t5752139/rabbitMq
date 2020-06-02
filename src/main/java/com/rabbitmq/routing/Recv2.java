package com.rabbitmq.routing;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.ConnertionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Recv2 {
    private static  final  String exchange = "tets_exchange_rouding";
    private static  final  String queue = "tets_queueu_rouding_02";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnertionUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(queue,false,false,false,null);
        channel.queueBind(queue,exchange,"error");
        channel.queueBind(queue,exchange,"info");
        channel.queueBind(queue,exchange,"waring");
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("消息2 :" + s);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //队列监听
        channel.basicConsume(queue,false,defaultConsumer);
    }
}

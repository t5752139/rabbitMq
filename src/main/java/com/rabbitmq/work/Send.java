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
        /**
         * 参数1 绑定队列
         * 参数2 用来定义队列特殊性是否要持久化 true持久化  false不持久化
         * 参数3 exclusive:是否独占队列  true独占队列 false不独占
         * 参数4 autodelete:是否在消费完成后自动删除队列 true删除 false不删除
         * 参数5 附加参数
         */
        channel.queueDeclare(QUEUE,false,false,false,null);
        for (int i = 0; i <50 ; i++) {
            //消息内容
            String s= "rabbitMq "+i;
            //发送消息
            /**
             * 参数1 交换机名称
             * 参数2 队列名称
             * 参数3 传递参数额外设置
             * 参数4 消息的具体内容
             */
            channel.basicPublish("",QUEUE,null,s.getBytes());
        }

        //关闭流
        channel.close();
        connection.close();
    }


}

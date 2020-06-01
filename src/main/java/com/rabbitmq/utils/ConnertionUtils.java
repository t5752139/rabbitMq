package com.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnertionUtils {
    /*
    获取工厂连接
     */
   public static Connection getConnection() throws IOException, TimeoutException {
       //定义一个工厂
       ConnectionFactory factory = new ConnectionFactory();
       //设置服务器地址
       factory.setHost("127.0.0.1");
       //端口
       //amqp 5672
       factory.setPort(5672);
       //virtual
       factory.setVirtualHost("/virtual_user");
       //账号
       factory.setUsername("user_admin");
       //密码
       factory.setPassword("123");
       //返回连接
       Connection connection = factory.newConnection();
       return connection;
   }
}

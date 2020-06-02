package com.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//扫描消费者
@ComponentScan(basePackages = "com.consumer")
@ComponentScan(basePackages = "com.springboot.rabbitmq")
public class AmqpSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpSpringBootApplication.class,args);
    }
}

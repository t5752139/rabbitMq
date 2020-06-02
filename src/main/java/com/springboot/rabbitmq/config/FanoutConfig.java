package com.springboot.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *  发布订阅模式 配置交换机类型为fanout
 * @author 田营科
 */
@Component
public class FanoutConfig {
    //定义sms 队列
    private static  final  String QUEUE_SMS = "work_sms";
    //定义email 队列
    private static  final  String QUEUE_EMAIL = "work_email";
    //定义交换机
    private static  final  String EXCHANGE = "exchange_fanout";

    //1 创建 队列
    @Bean
    public Queue fanoutQuereSms(){
        return new Queue(QUEUE_SMS);
    }
    //邮箱队列
    @Bean
    public Queue fanoutQuereEmail(){
        return new Queue(QUEUE_EMAIL);
    }
    //定义交换机
    // 如果更改订阅模式就是 类型加交换机    TopicExchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE);
    }
    //绑定队列根交换机

    /**
     *  邮箱和交换机进行绑定  参数名自一定是定义方法的名字
     * @param fanoutQuereSms   邮箱的名字
     * @param fanoutExchange    交换机的名字
     * @return
     */
    @Bean
    public Binding bindingExchangeSms(Queue fanoutQuereSms ,FanoutExchange fanoutExchange ){
        return BindingBuilder.bind(fanoutQuereSms).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeEmail(Queue fanoutQuereEmail ,FanoutExchange fanoutExchange ){
        return BindingBuilder.bind(fanoutQuereEmail).to(fanoutExchange);
    }
}

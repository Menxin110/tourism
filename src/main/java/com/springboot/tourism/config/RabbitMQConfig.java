package com.springboot.tourism.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author oneda
 * @version 菜鸟
 */

//@Configuration
public class RabbitMQConfig {

//    /**
//     *  自定义关于rabbitmq的消息转换器
//     *
//     * @return
//     */
//    @Bean
//    public MessageConverter messageConverter(){
//
//        return new Jackson2JsonMessageConverter();
//    }
//
//    /**
//     *  定义交换机
//     *
//     * @return
//     */
//    @Bean
//    public Exchange fanout_exchange(){
//
//        return ExchangeBuilder.fanoutExchange("fanout_exchange").build();
//    }
//
//    /**
//     * 定义消息队列
//     *
//     * @return
//     */
//    @Bean
//    public Queue fanout_queue_email(){
//        return new Queue("fanout_queue_email");
//    }
//    @Bean
//    public Queue fanout_queue_sms(){
//        return new Queue("fanout_queue_sms");
//    }
//
//    /**
//     * 将消息队列与交换机绑定
//     *
//     * @return
//     */
//    @Bean
//    public Binding bindingEmail(){
//        return BindingBuilder.bind(fanout_queue_email()).to(fanout_exchange()).with("").noargs();
//    }
//    @Bean
//    public Binding bindingSms(){
//        return BindingBuilder.bind(fanout_queue_sms()).to(fanout_exchange()).with("").noargs();
//    }
}

package com.springboot.tourism;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author oneda
 * @version 菜鸟
 *
 * 测试整合的rabbitmq是否能用
 */

//@SpringBootTest
public class RabbitMQTest {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Test
//    public void test(){
//
//        rabbitTemplate.convertAndSend("fanout_exchange", "", "向邮件消息队列发送");
//        System.out.println("向邮件消息队列发送");
//    }
//
//    @Test
//    public void testConn(){
//
//        System.out.println(rabbitTemplate);
//    }
}

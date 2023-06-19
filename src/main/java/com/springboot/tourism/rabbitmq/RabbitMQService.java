package com.springboot.tourism.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author oneda
 * @version 菜鸟
 */

//@Service
public class RabbitMQService {

//    @RabbitListener(queues = "fanout_queue_email")
//    public void listenerQueueEmail(Message message){
//        System.out.println("接收到消息了");
//
//        byte[] body = message.getBody();
//        String s = new String(body);
//        System.out.println("邮件消息队列接收到了" + s);
//    }
}

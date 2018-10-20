package com.objcoding.springboot;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/10/19
 */
@RestController
public class ProducerController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                LocalDateTime time = LocalDateTime.now();
                System.out.println("send message: " + time.toString());
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, time.toString());
            }
        }).start();
        return "ok";
    }

    @GetMapping("/sendMessage2")
    public String sendMessage2() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LocalDateTime time = LocalDateTime.now();
                System.out.println("send message2: " + time.toString());
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_2, time.toString());
            }
        }).start();
        return "ok";
    }

}

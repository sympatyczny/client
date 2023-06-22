package com.myProject.rabbitmq.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ClientMq {
    @Value("${queueName}")
    private String queueName;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/receiveMessage")
        public String get(){
        Object message = rabbitTemplate.receiveAndConvert(queueName);
        log.info("received Message: " + message + ", from queue: " + queueName);
        return message.toString();

    }

//    @RabbitListener(queues ="${queueName}")
//    public void rabbitListener(String msg){
//        System.out.println(msg.toString());
//    }

}
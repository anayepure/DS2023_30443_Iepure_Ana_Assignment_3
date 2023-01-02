package com.example.dsproject.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @RabbitListener(queues=MQConfig.QUEUE)
    public void listener(Message message)
    {
      System.out.println(message);
    }

}

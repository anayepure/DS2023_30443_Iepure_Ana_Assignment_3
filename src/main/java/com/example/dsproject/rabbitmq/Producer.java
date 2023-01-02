package com.example.dsproject.rabbitmq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Message message)
    {
      Message message1=new Message(message.getDevice_id(), message.getMeasurement_value());
      template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY, message1);
      return "Message published";
    }

    void readFromCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\DS\\ds-project -backend\\ds-project\\sensor.csv"))) {
            Double line;
            while ((line = Double.valueOf(br.readLine())) != null) {
              System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 5000L)
    public void scheduleFixedDelayTask() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\DS\\ds-project -backend\\ds-project\\sensor.csv"))) {
            Double line=Double.valueOf(br.readLine());
            Message message = null;
            while(line!=null)
            {
                message=new Message(1L,line);
                line = Double.valueOf(br.readLine());

            }
            template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

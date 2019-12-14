package com.ztt.kata.kafka.katakafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/add/")
    public void addMessage(@RequestBody String body){
        kafkaMessageProducer.sendMessage(body);
    }

}

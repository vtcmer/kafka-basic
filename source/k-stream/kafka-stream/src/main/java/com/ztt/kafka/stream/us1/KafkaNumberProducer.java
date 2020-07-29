package com.ztt.kafka.stream.us1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaNumberProducer {

    private long counter = 0;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //@Scheduled(fixedRate = 15000)
    public void produce(){
        System.out.println("Produced :: "+counter);
        kafkaTemplate.sendDefault(String.valueOf(counter++));
    }
}

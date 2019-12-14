package com.ztt.kata.kafka.katakafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestListener {

    @KafkaListener(topics = "${message.topic.name:na}", groupId = "${message.group.name:na}")
    public void listenTopic(String message){
        System.out.println("Received Message1: "+message);
    }

    @KafkaListener(topics = "${message.topic.name:na}", groupId = "${message.group.name2:otro_grupo}")
    public void listenTopic2(String message){
        System.out.println("Received Message2: "+message);
    }


}

package com.ztt.kafka.stream.us1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


//@Component
public class kafkaSquareCosumer {


    int cont =0;

    @KafkaListener(topics = "${kafka.topic.even-output}")
    //@KafkaListener(topics = "${kafka.topic.extractor-output}")
    public void cosume(String number){
        System.out.println("DATA:: "+(cont++)+" ::: "+number);
    }
}

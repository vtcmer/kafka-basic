package com.ztt.developer.example1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(groupId="myGroup", topics={"livestreams"})
    public void consume(ConsumerRecord<Long, String> record){
        System.out.println("Record 1 >> "+ record.key() + " :: "+record.value());
    }


    @KafkaListener(groupId="myGroup", topics={"livestreams"})
    public void consume2(ConsumerRecord<Long, String> record){
        System.out.println("Record  2 >> "+ record.key() + " :: "+record.value());
    }

    @KafkaListener(groupId="myGroup", topics={"livestreams"})
    public void consume3(ConsumerRecord<Long, String> record){
        System.out.println("Record  3 >> "+ record.key() + " :: "+record.value());
    }

    @KafkaListener(groupId="myGroupx", topics={"livestreams"})
    public void consume4(ConsumerRecord<Long, String> record){
        System.out.println("Record  X  >> "+ record.key() + " :: "+record.value());
    }

}

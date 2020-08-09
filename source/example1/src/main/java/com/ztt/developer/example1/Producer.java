package com.ztt.developer.example1;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class Producer {

    @Autowired
    private  KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    private NewTopic livestreamsTopic;

   @EventListener(ApplicationStartedEvent.class)
   @Scheduled(fixedDelay= 10000)
    public void produce(){
        LongStream.range(0,10).forEach(i -> {
            kafkaTemplate.send(livestreamsTopic.name(), String.valueOf(System.currentTimeMillis())).addCallback(result -> {
                if (result != null){
                    final long offset = result.getRecordMetadata().offset();
                   // System.out.println("Offset: "+offset);
                    final int partition = result.getRecordMetadata().partition();
                   // System.out.println("Partitiion: "+partition);

                }
            }, ex -> System.err.println("not today"));
        });

    }
}

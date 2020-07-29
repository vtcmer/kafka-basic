package com.ztt.kafka.stream.m30;

import com.ztt.kafka.stream.m30.model.PhotoCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaM30Producer {

    @Autowired
    private M30PhotoCameraConversor m30PhotoCameraConversor;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic.extractor}")
    private String topic;

    public void produce(final PhotoCamera photoCamera){
        kafkaTemplate.send(this.topic, this.m30PhotoCameraConversor.objectToJson(photoCamera));
    }


}

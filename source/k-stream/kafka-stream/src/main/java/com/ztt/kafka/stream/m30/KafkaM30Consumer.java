package com.ztt.kafka.stream.m30;


import com.ztt.kafka.stream.m30.model.PhotoCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaM30Consumer {

    @Autowired
    private M30PhotoCameraConversor m30PhotoCameraConversor;


   // @KafkaListener(topics = "${kafka.topic.extractor-output}")
    @KafkaListener(topics = "${kafka.topic.extractor}")
    public void cosume(String data){

        PhotoCamera photoCamera = this.m30PhotoCameraConversor.jsonToObject(data);
        System.out.println("PROVINCIA::: " +photoCamera.getName() + " ::: "+ photoCamera.getDate());
        //System.out.println("PROVINCIA::: "+(cont++)+" ::: " +data);
    }

}

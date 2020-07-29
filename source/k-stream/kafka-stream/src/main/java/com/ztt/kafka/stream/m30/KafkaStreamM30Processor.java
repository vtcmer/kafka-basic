package com.ztt.kafka.stream.m30;

import com.ztt.kafka.stream.commons.IStreamProcessor;
import com.ztt.kafka.stream.m30.model.PhotoCamera;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class KafkaStreamM30Processor implements IStreamProcessor {

    @Value("${kafka.topic.extractor-output}")
    private String ouputTopic;

    @Autowired
    private M30PhotoCameraConversor m30PhotoCameraConversor;

    private List<String> provincias = new ArrayList<String>();
    private Random rn = new Random();

    @PostConstruct
    private void populateProvincias() {
        provincias.add("Madrid");
        provincias.add("León");
        provincias.add("A Coruña");
        provincias.add("Oviedo");
        provincias.add("Valladolid");
    }

    @Bean
    public java.util.function.Consumer<KStream<Object, String>> process() {
        return null;
    }

    @Override
    public void process(KStream<String, String> stream) {
        stream.mapValues( data -> {
           // System.out.println("Enriqueciendo el dato Camera:: "+data);
            return this.updateData(data);
        }).to(ouputTopic);
    }

    private String updateData(String input){
        String output = null;
        PhotoCamera photoCamera = this.m30PhotoCameraConversor.jsonToObject(input);
        String provincia = this.getProvinciaByCoordinates(photoCamera.getLatitude(), photoCamera.getLongitude());
        photoCamera.setProvincia(provincia);
        output = this.m30PhotoCameraConversor.objectToJson(photoCamera);
        return output;
    }

    private String getProvinciaByCoordinates(BigDecimal latitude, BigDecimal longitude){
        String provincia = "----";

        int index = rn.nextInt(5);
        if ((index >=0) && (index < 5)){
            provincia = this.provincias.get(index);
        } else {
            provincia = "INDEX-"+index;
        }

        return provincia;
    }


}

package com.ztt.kafka.stream.m30;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztt.kafka.stream.m30.model.PhotoCamera;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class M30PhotoCameraConversor {

    /**
     * Conversión de objeto a String en formato json
     * @param photoCamera
     * @return
     */
    public String objectToJson(PhotoCamera photoCamera){
        String jsonResult = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonResult = objectMapper.writeValueAsString(photoCamera);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }


    public PhotoCamera jsonToObject(String jsonObject){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        PhotoCamera photoCamera = null;
        try {
            photoCamera =  objectMapper.readValue(jsonObject, PhotoCamera.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return photoCamera;
    }

}

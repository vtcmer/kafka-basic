package com.ztt.kafka.stream.m30;

import com.ztt.kafka.stream.m30.model.PhotoCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataExtractor {

    @Value(value = "${extractor.cameras.url}")
    private String url;

    @Autowired
    private KafkaM30Producer kafkaM30Producer;

    public void execute(){
        this.extractData();
    }



    private void extractData(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Camara");
            int cont = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                PhotoCamera photo = this.processNode(node);
                this.kafkaM30Producer.produce(photo);
                System.out.println(cont++);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PhotoCamera processNode(Node node){
        PhotoCamera photo = null;
        if (node.getNodeType() == Node.ELEMENT_NODE){
            photo = new PhotoCamera();
            Element elem = (Element) node;
            photo.setName(elem.getElementsByTagName("Nombre").item(0).getTextContent());
            photo.setUrl(elem.getElementsByTagName("URL").item(0).getTextContent());
            photo.setLatitude(new BigDecimal(elem.getElementsByTagName("Latitud").item(0).getTextContent()));
            photo.setLongitude(new BigDecimal(elem.getElementsByTagName("Longitud").item(0).getTextContent()));
            photo.setDate(new Date());
        }
        return photo;
    }


}

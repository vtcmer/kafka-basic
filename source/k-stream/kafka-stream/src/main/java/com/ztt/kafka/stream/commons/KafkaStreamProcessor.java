package com.ztt.kafka.stream.commons;

import com.ztt.kafka.stream.m30.KafkaStreamM30Processor;
import com.ztt.kafka.stream.us1.KafkaNumberProducer;
import com.ztt.kafka.stream.us1.KafkaStreamNumberProcessor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
//@EnableKafkaStreams
public class KafkaStreamProcessor {

    @Value("${kafka.topic.input}")
    private String inputTopic;

    @Value("${kafka.topic.extractor}")
    private String topicExtractor;

    @Autowired
    private KafkaStreamNumberProcessor kafkaStreamNumberProcessor;
    @Autowired
    private KafkaStreamM30Processor kafkaStreamM30Processor;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        //config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "APP1");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new KafkaStreamsConfiguration(config);
    }


    //@Bean
    public KStream<String,String> kStream(StreamsBuilder kStreamBuilder){
        //KStream<String, String> stream = kStreamBuilder.stream(inputTopic);
        //this.kafkaStreamNumberProcessor.process(stream);

        KStream<String, String> streamM30 = kStreamBuilder.stream(topicExtractor);
        this.kafkaStreamM30Processor.process(streamM30);

        return streamM30;

    }

    //@Bean
    public KStream<String,String> kStream2(StreamsBuilder kStreamBuilder){
        KStream<String, String> stream = kStreamBuilder.stream(inputTopic);
        this.kafkaStreamNumberProcessor.process(stream);
        return stream;

    }
}

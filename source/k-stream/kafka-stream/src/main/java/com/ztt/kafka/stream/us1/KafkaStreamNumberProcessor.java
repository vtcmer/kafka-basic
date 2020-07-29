package com.ztt.kafka.stream.us1;

import com.ztt.kafka.stream.commons.IStreamProcessor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamNumberProcessor implements IStreamProcessor {

    @Value("${kafka.topic.even-output}")
    private String ouputTopic;

    @Override
    public void process(KStream<String, String> stream) {
        stream.filter((k,v) -> Long.valueOf(v) % 2 == 0)
                .mapValues(v -> {
                    System.out.println("Processing Number:: "+v);
                    Long result = Long.valueOf(v);
                    result = result * result;
                    return String.valueOf(result);
                }).to(ouputTopic);
    }
}

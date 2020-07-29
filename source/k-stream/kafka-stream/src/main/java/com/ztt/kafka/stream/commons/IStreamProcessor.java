package com.ztt.kafka.stream.commons;

import org.apache.kafka.streams.kstream.KStream;

public interface IStreamProcessor {

    /**
     * Procesamiento
     * @param stream
     */
    void process(KStream<String,String> stream);

}

package com.meowricio.kafkaproject.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private String receivedData;

    @KafkaListener(topics = "meow", groupId = "groupId")
    void listener(String data) {
        LOGGER.info("received payload='{}'", data);

        System.out.println("Listener receive: " + data);
        receivedData = data;
        System.out.println(receivedData + "hey hey");
    }

    public String getReceivedData() {
        return receivedData;
    }

    public void setReceivedData(String receivedData) {
        this.receivedData = receivedData;
    }
//public final List<String> messages = new ArrayList<>();
//
//    @KafkaListener(topics = "${app.kafka.topic}", gr)
//    public void consume(@Payload String value) {
//        messages.add(value);
//        for (String message: messages) {
//            System.out.println(message);
//        }
//    }
}

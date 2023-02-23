package com.meowricio.kafkaproject;

import com.meowricio.kafkaproject.consumer.KafkaConsumer;
import com.meowricio.kafkaproject.producer.KafkaProducer;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


@Testcontainers
@SpringBootTest
public class KafkaTestContainer {
    @Container
    private static final KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    private static void kafkaProperties(DynamicPropertyRegistry registry) {
        String host = kafkaContainer.getBootstrapServers().substring(9);
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        System.out.println(kafkaContainer.getBootstrapServers());;
    }

    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private KafkaConsumer kafkaConsumer;

//    @Value(value = "${app.kafka.topic}")
//    private String topic ;

    @Test
    public void testContainer() throws InterruptedException {
        String data = "meow";

//        Thread.sleep(10000);
        kafkaProducer.send("meow", data);
        assertThat(kafkaConsumer.getReceivedData(), containsString(data));

    }

}

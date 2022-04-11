package com.example.kafka;

import com.example.kafka.dto.KafkaMessage;
import com.example.kafka.service.KafkaMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    private final KafkaMessageSender messageSender;

    @Override
    // Just for local testing
    public void run(String... args) throws Exception {

        //Send 3 test messages to kafka topic
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setAuthor("John Smith");
        kafkaMessage.setMessage("Hello Elon Mask!");
        messageSender.sendMessage(kafkaMessage);

        kafkaMessage = new KafkaMessage();
        kafkaMessage.setAuthor("John Doe");
        kafkaMessage.setMessage("I am still alive");
        messageSender.sendMessage(kafkaMessage);

        kafkaMessage = new KafkaMessage();
        kafkaMessage.setAuthor("Will Smith");
        kafkaMessage.setMessage("Keep on rolling baby");
        messageSender.sendMessage(kafkaMessage);
    }
}

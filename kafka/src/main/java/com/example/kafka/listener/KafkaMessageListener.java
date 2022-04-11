package com.example.kafka.listener;

import com.example.kafka.dto.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageListener implements MessageListener<String, KafkaMessage> {

    public static final String TOPIC_NAME = "some-kafka-topic";

    @Override
    @KafkaListener(topics = TOPIC_NAME)
    public void onMessage(ConsumerRecord<String, KafkaMessage> consumerRecord) {
        KafkaMessage kafkaMessage = consumerRecord.value();

        String user = kafkaMessage.getAuthor();
        String message = kafkaMessage.getMessage();

        log.info("User : {}", user);
        log.info("Send message: {}", message);
    }
}

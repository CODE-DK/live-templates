package com.example.kafka.service;

import com.example.kafka.dto.KafkaMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import static com.example.kafka.listener.KafkaMessageListener.TOPIC_NAME;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaMessageSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(KafkaMessage kafkaMessage) {
        ProducerRecord<String, Object> producerRecord = toRecord(TOPIC_NAME, kafkaMessage);

        ListenableFuture<SendResult<String, Object>> listenableFuture
                = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        log.error("Message can't be send to kafka. Reason: {}", throwable.getMessage(), throwable);
    }

    private void onSuccess(SendResult<String, Object> sendResult) {
        assert sendResult != null;

        ProducerRecord<String, Object> producerRecord = sendResult.getProducerRecord();
        log.info("Message was send to topic: {} with key: {} value: {}",
                producerRecord.topic(),
                producerRecord.key(),
                producerRecord.value()
        );
    }

    private ProducerRecord<String, Object> toRecord(String topic, KafkaMessage message) {
        return new ProducerRecord<>(topic, message.getId().toString(), message);
    }
}

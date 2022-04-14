package com.example.ibmmq.service;

import com.example.ibmmq.dto.IbmMessage;
import com.ibm.mq.jms.MQQueue;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

import static com.example.ibmmq.config.ImbConfig.IBM_MESSAGES_QUEUE;

@Slf4j
@Service
@AllArgsConstructor
public class IbmMessageSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(String selector, IbmMessage ibmMessage) {
        log.info("Send message to: {} with payload: {} and selector: {}", IBM_MESSAGES_QUEUE, ibmMessage, selector);
        jmsTemplate.convertAndSend(toDestination(IBM_MESSAGES_QUEUE), ibmMessage, postProcessor -> {
            postProcessor.setStringProperty("listener", selector);
            return postProcessor;
        });
    }

    public void sendMessage(IbmMessage ibmMessage) {
        log.info("Send message to: {} with payload: {}", IBM_MESSAGES_QUEUE, ibmMessage);
        jmsTemplate.convertAndSend(toDestination(IBM_MESSAGES_QUEUE), ibmMessage);
    }

    @SneakyThrows
    @SuppressWarnings("SameParameterValue")
    private Destination toDestination(String queue) {
        return new MQQueue(queue);
    }
}

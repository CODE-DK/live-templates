package com.example.ibmmq.listener;

import com.example.ibmmq.dto.IbmMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

import static com.example.ibmmq.config.ImbConfig.CONTAINER_FACTORY;
import static com.example.ibmmq.config.ImbConfig.IBM_MESSAGES_QUEUE;

@Slf4j
@Component
@AllArgsConstructor
public class IbmMessageListener implements MessageListener {

    private final ObjectMapper objectMapper;

    @Override
    @JmsListener(
            // NOTE:
            // using selector you work over concurrent env!!
            // always use selector in each listener or you may lose some data
            destination = IBM_MESSAGES_QUEUE,
            containerFactory = CONTAINER_FACTORY
    )
    public void onMessage(Message message) {
        IbmMessage messageBody = readMessage(message);
        log.info("0) Receive new message from: {} with payload: {}", IBM_MESSAGES_QUEUE, messageBody);
    }

    @SneakyThrows
    private IbmMessage readMessage(Message message) {
        byte[] byteArray = (byte[]) message.getBody(Object.class);
        String body = new String(byteArray);

        return objectMapper.readValue(body, IbmMessage.class);
    }
}

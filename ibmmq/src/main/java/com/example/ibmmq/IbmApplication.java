package com.example.ibmmq;

import com.example.ibmmq.dto.IbmMessage;
import com.example.ibmmq.service.IbmMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class IbmApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IbmApplication.class, args);
    }

    private final IbmMessageSender messageSender;

    @Override
    public void run(String... args) throws Exception {
        IbmMessage ibmMessage = new IbmMessage();
        ibmMessage.setAuthor("John Smith");
        ibmMessage.setMessage("Eat more this tasty cakes");

        messageSender.sendMessage("first", ibmMessage);

        ibmMessage = new IbmMessage();
        ibmMessage.setAuthor("John Doe");
        ibmMessage.setMessage("I'm still alive");

        messageSender.sendMessage("second", ibmMessage);

        ibmMessage = new IbmMessage();
        ibmMessage.setAuthor("Mike Tyson");
        ibmMessage.setMessage("I gonna touch your face!! Ha-ha-ha");

        messageSender.sendMessage(ibmMessage);
    }
}

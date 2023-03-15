package com.organization.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    public static final String BE_SUCCESS_MESSAGE = "BE works";
    public static final String DB_SUCCESS_MESSAGE = "DB works";
    public static final String DB_ERROR_MESSAGE = "DB error message:";
    public static final String STORED_MESSAGES = "Stored messages:";

    @Autowired
    MessageRepository repo;

    @GetMapping(value = "/")
    public String getMessage() {
        StringBuilder result = new StringBuilder(BE_SUCCESS_MESSAGE).append(System.lineSeparator());

        try {
            List<Message> messages = repo.findAll();
            String results = messages.stream().map(Message::getMessage).collect(Collectors.joining(System.lineSeparator()));
            result.append(DB_SUCCESS_MESSAGE).append(System.lineSeparator()).append(STORED_MESSAGES).append(System.lineSeparator()).append(results);
        } catch (Exception e) {
            e.printStackTrace();
            result.append(DB_ERROR_MESSAGE).append(e.getMessage());
        }

        return result.toString();
    }

    @GetMapping(value = "/{message}")
    public String saveMessage(@PathVariable String message) {
        return "Message `%s` was saved successfully".formatted(repo.save(new Message(message)).getMessage());
    }
}

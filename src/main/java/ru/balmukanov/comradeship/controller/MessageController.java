package ru.balmukanov.comradeship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.balmukanov.comradeship.dto.EventType;
import ru.balmukanov.comradeship.dto.ObjectType;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.entity.Views;
import ru.balmukanov.comradeship.repository.MessageRepository;
import ru.balmukanov.comradeship.util.WebSocketSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;

    private final BiConsumer<EventType, Message> webSocketSender;

    @Autowired
    public MessageController(MessageRepository messageRepository, WebSocketSender webSocketSender) {
        this.messageRepository = messageRepository;
        this.webSocketSender = webSocketSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return this.messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullName.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());

        Message createdMessage = messageRepository.save(message);
        this.webSocketSender.accept(EventType.CREATE, createdMessage);

        return createdMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        Message updatedMessage = this.messageRepository.save(messageFromDb);
        this.webSocketSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        this.messageRepository.delete(message);
        this.webSocketSender.accept(EventType.DELETE, message);
    }
}

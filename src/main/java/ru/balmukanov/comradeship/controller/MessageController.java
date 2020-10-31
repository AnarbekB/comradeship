package ru.balmukanov.comradeship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.entity.Views;
import ru.balmukanov.comradeship.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;

    private final SimpMessagingTemplate template;

    @Autowired
    public MessageController(MessageRepository messageRepository, SimpMessagingTemplate template) {
        this.messageRepository = messageRepository;
        this.template = template;
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
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return this.messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        this.messageRepository.delete(message);
    }

    @MessageMapping("changeMessage")
    public void change(Message message) {
        if (message.getId() == 0) {
            message.setCreatedAt(LocalDateTime.now());
        }

        Message messageSave = this.messageRepository.save(message);
        this.template.convertAndSend("/topic/activity", messageSave);
    }
}

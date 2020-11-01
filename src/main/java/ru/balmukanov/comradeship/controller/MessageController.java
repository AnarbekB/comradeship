package ru.balmukanov.comradeship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import ru.balmukanov.comradeship.dto.MessageDto;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.util.Views;
import ru.balmukanov.comradeship.service.MessageService;
import ru.balmukanov.comradeship.transformer.MessageTransformer;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<MessageDto> list() {
        return this.messageService.all();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullName.class)
    public MessageDto getOne(@PathVariable("id") Message message) {
        return MessageTransformer.toDto(message);
    }

    @PostMapping
    public MessageDto create(@RequestBody Message message) {
        return this.messageService.create(message);
    }

    @PutMapping("{id}")
    public MessageDto update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        return this.messageService.update(messageFromDb, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        this.messageService.delete(message.getId());
    }
}

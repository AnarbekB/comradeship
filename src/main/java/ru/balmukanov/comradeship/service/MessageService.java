package ru.balmukanov.comradeship.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.balmukanov.comradeship.dto.EventType;
import ru.balmukanov.comradeship.dto.MessageDto;
import ru.balmukanov.comradeship.dto.ObjectType;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.util.Views;
import ru.balmukanov.comradeship.exceptions.NotFoundException;
import ru.balmukanov.comradeship.repository.MessageRepository;
import ru.balmukanov.comradeship.transformer.MessageTransformer;
import ru.balmukanov.comradeship.util.WebSocketSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final BiConsumer<EventType, MessageDto> webSocketSender;

    private final MetaParser metaParser;

    public MessageService(
            MessageRepository messageRepository,
            WebSocketSender webSocketSender,
            MetaParser metaParser
    ) {
        this.messageRepository = messageRepository;
        this.webSocketSender = webSocketSender.getSender(ObjectType.MESSAGE, Views.FullName.class);
        this.metaParser = metaParser;
    }

    public MessageDto create(Message message) {
        message.setCreatedAt(LocalDateTime.now());

        try {
            message = this.metaParser.fillMeta(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message createdMessage = messageRepository.save(message);
        MessageDto dto = MessageTransformer.toDto(createdMessage);

        this.webSocketSender.accept(EventType.CREATE, dto);

        return dto;
    }

    public MessageDto update(Message messageFromDb, Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        try {
            messageFromDb = this.metaParser.fillMeta(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message updatedMessage = this.messageRepository.save(messageFromDb);
        MessageDto dto = MessageTransformer.toDto(updatedMessage);

        this.webSocketSender.accept(EventType.UPDATE, dto);

        return dto;
    }

    public void delete(long id) {
        Message message = this.messageRepository.findById(id).orElseThrow(NotFoundException::new);
        this.messageRepository.delete(message);

        MessageDto dto = MessageTransformer.toDto(message);
        this.webSocketSender.accept(EventType.DELETE, dto);
    }

    public List<MessageDto> all() {
        return MessageTransformer.toDtoList(this.messageRepository.findAll());
    }
}

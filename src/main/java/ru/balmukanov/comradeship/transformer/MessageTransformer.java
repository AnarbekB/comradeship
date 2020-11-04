package ru.balmukanov.comradeship.transformer;

import ru.balmukanov.comradeship.dto.MessageDto;
import ru.balmukanov.comradeship.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageTransformer {
    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setCreatedAt(message.getCreatedAt());
        messageDto.setText(message.getText());
        messageDto.setAuthor(message.getAuthor());
        messageDto.setComments(message.getComments());

        if (message.getLink() != null) {
            messageDto.setLink(message.getLink().getLink());
            messageDto.setLinkCover(message.getLink().getCover());
            messageDto.setLinkTitle(message.getLink().getTitle());
            messageDto.setLinkDescription(message.getLink().getDescription());
        }

        return messageDto;
    }

    public static List<MessageDto> toDtoList(List<Message> messages) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        messages.forEach(message -> messageDtoList.add(toDto(message)));

        return messageDtoList;
    }
}
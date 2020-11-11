package ru.balmukanov.comradeship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.util.Views;

@Data
public class CommentDto {
    @JsonView(Views.IdName.class)
    private long id;

    @JsonView(Views.IdName.class)
    private String text;

    private MessageDto message;

    @JsonView(Views.FullMessage.class)
    private User author;
}

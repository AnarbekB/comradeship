package ru.balmukanov.comradeship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.balmukanov.comradeship.entity.Comment;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.util.Views;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageDto {
    @JsonView(Views.Id.class)
    private long id;

    @JsonView(Views.IdName.class)
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime createdAt;

    @JsonView(Views.FullMessage.class)
    private String link;

    @JsonView(Views.FullMessage.class)
    private String linkTitle;

    @JsonView(Views.FullMessage.class)
    private String linkDescription;

    @JsonView(Views.FullMessage.class)
    private String linkCover;

    @JsonView(Views.FullMessage.class)
    private User author;

    @JsonView(Views.FullMessage.class)
    private List<CommentDto> comments;
}

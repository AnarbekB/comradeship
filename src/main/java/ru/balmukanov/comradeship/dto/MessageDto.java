package ru.balmukanov.comradeship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.balmukanov.comradeship.entity.Link;
import ru.balmukanov.comradeship.entity.Views;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class MessageDto {
    @JsonView(Views.Id.class)
    private long id;

    @JsonView(Views.IdName.class)
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullName.class)
    private LocalDateTime createdAt;

    @JsonView(Views.FullName.class)
    private String link;

    @JsonView(Views.FullName.class)
    private String linkTitle;

    @JsonView(Views.FullName.class)
    private String linkDescription;

    @JsonView(Views.FullName.class)
    private String linkCover;
}

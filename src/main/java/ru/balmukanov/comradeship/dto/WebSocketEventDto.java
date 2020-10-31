package ru.balmukanov.comradeship.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.balmukanov.comradeship.entity.Views;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WebSocketEventDto {
    private  ObjectType objectType;

    private EventType eventType;

    @JsonRawValue
    private String body;
}

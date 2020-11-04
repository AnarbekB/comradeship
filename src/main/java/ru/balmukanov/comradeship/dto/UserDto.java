package ru.balmukanov.comradeship.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.balmukanov.comradeship.entity.Message;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.util.Views;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto implements Serializable {

    @JsonView(Views.IdName.class)
    public String id;

    @JsonView(Views.IdName.class)
    public String name;

    @JsonView(Views.IdName.class)
    public String picture;

    public String email;

    public String gender;

    public String locale;

    public LocalDateTime lastVisitAt;

    private List<Message> messages;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.picture = user.getPicture();
        dto.email = user.getEmail();
        dto.gender = user.getGender();
        dto.locale = user.getLocale();
        dto.lastVisitAt = user.getLastVisitAt();
        dto.messages = user.getMessages();

        return dto;
    }
}

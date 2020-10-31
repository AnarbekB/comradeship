package ru.balmukanov.comradeship.dto;

import ru.balmukanov.comradeship.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDto implements Serializable {

    public String id;

    public String name;

    public String picture;

    public String email;

    public String gender;

    public String locale;

    public LocalDateTime lastVisitAt;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.picture = user.getPicture();
        dto.email = user.getEmail();
        dto.gender = user.getGender();
        dto.locale = user.getLocale();
        dto.lastVisitAt = user.getLastVisitAt();

        return dto;
    }
}

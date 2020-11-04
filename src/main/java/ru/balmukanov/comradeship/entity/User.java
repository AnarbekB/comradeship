package ru.balmukanov.comradeship.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String id;

    private String name;

    private String picture;

    private String email;

    private String gender;

    private String locale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisitAt;

    private String password;

    @OneToMany(mappedBy = "author")
    private List<Message> messages;
}

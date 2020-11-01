package ru.balmukanov.comradeship.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

//todo у сообщения может быть несколько ссылок
@Data
@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;

    @Column(nullable = false)
    @JsonView(Views.FullName.class)
    private String link;

    @Column()
    @JsonView(Views.FullName.class)
    private String title;

    @Column()
    @JsonView(Views.FullName.class)
    private String description;

    @Column()
    @JsonView(Views.FullName.class)
    private String cover;
}

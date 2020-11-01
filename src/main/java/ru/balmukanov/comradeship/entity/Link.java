package ru.balmukanov.comradeship.entity;

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
    private String link;

    @Column()
    private String title;

    @Column()
    private String description;

    @Column()
    private String cover;
}

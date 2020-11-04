package ru.balmukanov.comradeship.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
@EqualsAndHashCode(of = { "id" })
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User author;
}

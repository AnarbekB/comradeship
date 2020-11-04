package ru.balmukanov.comradeship.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "message")
    private Link link;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    private List<Comment> comments;
}

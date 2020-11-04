package ru.balmukanov.comradeship.service;

import org.springframework.stereotype.Service;
import ru.balmukanov.comradeship.dto.CommentDto;
import ru.balmukanov.comradeship.entity.Comment;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.repository.CommentRepository;
import ru.balmukanov.comradeship.transformer.CommentTransformer;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto create(Comment comment, User user) {
        comment.setAuthor(user);

        this.commentRepository.save(comment);

        return CommentTransformer.toDto(comment);
    }
}

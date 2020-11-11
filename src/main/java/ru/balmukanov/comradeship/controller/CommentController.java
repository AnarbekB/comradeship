package ru.balmukanov.comradeship.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.balmukanov.comradeship.dto.CommentDto;
import ru.balmukanov.comradeship.entity.Comment;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.service.CommentService;
import ru.balmukanov.comradeship.util.Views;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @JsonView(Views.FullMessage.class)
    public CommentDto create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user
    ) {
        return this.commentService.create(comment, user);
    }
}

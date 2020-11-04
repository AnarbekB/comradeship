package ru.balmukanov.comradeship.transformer;

import ru.balmukanov.comradeship.dto.CommentDto;
import ru.balmukanov.comradeship.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentTransformer {
    public static CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setText(comment.getText());
        commentDto.setMessage(comment.getMessage());

        return commentDto;
    }

    public static List<CommentDto> toDtoList(List<Comment> comments) {
        List<CommentDto> commentsDtoList = new ArrayList<>();
        comments.forEach(comment -> commentsDtoList.add(toDto(comment)));

        return commentsDtoList;
    }
}

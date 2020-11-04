package ru.balmukanov.comradeship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.comradeship.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

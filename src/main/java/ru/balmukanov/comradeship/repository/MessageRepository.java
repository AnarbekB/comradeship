package ru.balmukanov.comradeship.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.comradeship.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @EntityGraph(attributePaths = { "comments" })
    List<Message> findAll();

}

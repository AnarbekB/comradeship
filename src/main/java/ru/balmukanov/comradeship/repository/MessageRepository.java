package ru.balmukanov.comradeship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.comradeship.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

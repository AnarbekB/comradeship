package ru.balmukanov.comradeship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.comradeship.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}

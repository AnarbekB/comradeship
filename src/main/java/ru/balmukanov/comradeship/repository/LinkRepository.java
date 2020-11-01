package ru.balmukanov.comradeship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.comradeship.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}

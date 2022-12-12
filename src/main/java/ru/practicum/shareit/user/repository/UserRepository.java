package ru.practicum.shareit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.user.User;

import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {
}


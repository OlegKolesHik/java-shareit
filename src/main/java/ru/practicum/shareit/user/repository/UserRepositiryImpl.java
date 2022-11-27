package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;

import java.util.HashMap;
import java.util.stream.Stream;

@Repository
@Slf4j
public class UserRepositiryImpl implements UserRepository {
    private final HashMap<Long, User> users = new HashMap<>();
    private long count;

    @Override
    public Stream<User> findAll() {
        return users.values().stream();
    }

    @Override
    public User getById(long userId) {
        return users.get(userId);
    }

    @Override
    public User add(User user) {
        if (user.getId() == 0) {
            user.setId(++count);
        }
        users.put(user.getId(), user);
        return getById(user.getId());
    }

    @Override
    public void delete(long userId) {
        users.remove(userId);
    }
}

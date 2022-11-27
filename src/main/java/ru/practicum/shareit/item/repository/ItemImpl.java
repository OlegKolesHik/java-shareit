package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
@Repository
public class ItemImpl implements ItemRepository {
    private final HashMap<Long, Item> items = new HashMap<>();
    private final HashMap<Long, Set<Long>> userItems = new HashMap<>();

    private long count;

    @Override
    public Stream<Item> findAll() {
        return items.values().stream();
    }

    @Override
    public Item getById(long itemId) {
        return items.get(itemId);
    }

    @Override
    public Item add(Item item, long userId) {
        Set<Long> savedUserItems;
        if (userItems.get(userId) != null) {
            savedUserItems = userItems.get(userId);
        } else {
            savedUserItems = new HashSet<>();
        }
        if (item.getId() == 0) {
            item.setId(++count);
        }
        items.put(item.getId(), item);

        savedUserItems.add(item.getId());
        userItems.put(userId, savedUserItems);
        return items.get(item.getId());
    }

    @Override
    public Set<Long> getUserItems(long userId) {
        return userItems.get(userId);
    }
}


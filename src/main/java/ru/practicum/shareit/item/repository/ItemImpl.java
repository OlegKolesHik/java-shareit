package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Slf4j
@Repository
public class ItemImpl implements ItemRepositoryImp {
    public final HashMap<Long, Item> items = new HashMap<>();
    public final HashMap<Long, Set<Long>> userItems = new HashMap<>();



    @Override
    public Collection<Item> findAll() {
        return items.values();
    }

    @Override
    public Item getById(long itemId) {
        return items.get(itemId);
    }

    @Override
    public Item add(Item item, long userId) {
        return items.get(item.getId());
    }

    @Override
    public Set<Long> getUserItems(long userId) {
        return userItems.get(userId);
    }
}


package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Slf4j
@Repository
public class ItemImpl implements ItemRepositoryImp {
    public final HashMap<Long, ItemDto> items = new HashMap<>();
    public final HashMap<Long, Set<Long>> userItems = new HashMap<>();



    @Override
    public Collection<ItemDto> findAll() {
        return items.values();
    }

    @Override
    public ItemDto getById(long itemId) {
        return items.get(itemId);
    }

    @Override
    public ItemDto add(ItemDto item, long userId) {
        return items.get(item.getId());
    }

    @Override
    public Set<Long> getUserItems(long userId) {
        return userItems.get(userId);
    }
}


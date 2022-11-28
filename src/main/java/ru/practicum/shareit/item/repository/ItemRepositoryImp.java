package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

public interface ItemRepositoryImp {

    Collection<Item> findAll();

    Item getById(long itemId);

    Item add(Item item, long userId);

    Set<Long> getUserItems(long userId);
}

package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Set;

public interface ItemRepositoryImp {

    Collection<ItemDto> findAll();

    ItemDto getById(long itemId);

    ItemDto add(ItemDto item, long userId);

    Set<Long> getUserItems(long userId);
}

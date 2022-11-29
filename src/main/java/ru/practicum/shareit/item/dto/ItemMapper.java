package ru.practicum.shareit.item.dto;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

    public static ItemDto mapping(ItemDto item) {
        return new ItemDto(item.getId(), item.getAvailable(), item.getName(), item.getDescription());
    }
}

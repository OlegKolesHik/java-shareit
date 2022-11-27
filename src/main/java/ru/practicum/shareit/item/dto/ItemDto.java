package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private long id;
    private boolean available;
    private String name;
    private String description;

    public ItemDto(long id, Boolean available, String name, String description) {
        this.id = id;
        this.available = available;
        this.name = name;
        this.description = description;
    }
}

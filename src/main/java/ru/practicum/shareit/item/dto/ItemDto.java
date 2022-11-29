package ru.practicum.shareit.item.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemDto {
    private long id;
    @NotNull
    private Boolean available;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    public ItemDto(long id, Boolean available, String name, String description) {
        this.id = id;
        this.available = available;
        this.name = name;
        this.description = description;
    }
}

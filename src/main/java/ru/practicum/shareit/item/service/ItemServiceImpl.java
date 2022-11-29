package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemImpl;
import ru.practicum.shareit.item.repository.ItemRepositoryImp;
import ru.practicum.shareit.user.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepositoryImp itemRepository;
    private final UserService userService;
    private long count;
    private final ItemImpl itemImpl;

    @Override
    public ItemDto addItem(ItemDto item, long userId) {
        if (userService.getUserById(userId) == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        Set<Long> savedUserItems;
        if (itemImpl.userItems.get(userId) != null) {
            savedUserItems = itemImpl.userItems.get(userId);
        } else {
            savedUserItems = new HashSet<>();
        }
        if (item.getId() == 0) {
            item.setId(++count);
        }
        itemImpl.items.put(item.getId(), item);

        savedUserItems.add(item.getId());
        itemImpl.userItems.put(userId, savedUserItems);

        return ItemMapper.mapping(itemRepository.add(item, userId));
    }

    @Override
    public ItemDto updateItem(ItemDto updatedItem, long itemId, long userId) {
        ItemDto item = itemRepository.getById(itemId);
        Set<Long> userItems = itemRepository.getUserItems(userId);
        if (userItems == null || !userItems.contains(itemId)) {
            throw new NotFoundException("Предмет не найден");
        }
        if (item == null) {
            throw new NotFoundException("Товар не найден");
        }
        if (userService.getUserById(userId) == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (updatedItem.getName() != null) {
            item.setName(updatedItem.getName());
        }
        if (updatedItem.getDescription() != null) {
            item.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getAvailable() != null) {
            item.setAvailable(updatedItem.getAvailable());
        }
        return ItemMapper.mapping(itemRepository.add(item, userId));
    }


    @Override
    public ItemDto getById(long itemId) {
        return ItemMapper.mapping(itemRepository.getById(itemId));
    }

    @Override
    public Stream<ItemDto> searchItem(String text) {
        if (text.isEmpty()) {
            return Stream.empty();
        }
        Stream<ItemDto> findByName = itemRepository.findAll()
                .stream().filter((ItemDto item) -> item.getName().toLowerCase().contains(text.toLowerCase()));
        Stream<ItemDto> findByDescription = itemRepository.findAll()
                .stream().filter((ItemDto item) -> item.getDescription().toLowerCase().contains(text.toLowerCase()));
        return Stream.concat(findByDescription, findByName)
                .distinct()
                .filter(ItemDto::getAvailable)
                .map(ItemMapper::mapping);
    }

    @Override
    public Stream<ItemDto> getItems(long userId) {
        if (userId == 0) {
            return getAllItems();
        } else {
            if (userService.getUserById(userId) == null) {
                throw new NotFoundException("Пользователь не найден");
            }
            return getItemByUser(userId);
        }
    }

    private Stream<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(ItemMapper::mapping);
    }

    private Stream<ItemDto> getItemByUser(long userId) {
        return itemRepository.getUserItems(userId).stream().map(itemRepository::getById).map(ItemMapper::mapping);
    }
}

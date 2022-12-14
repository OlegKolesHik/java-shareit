package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ru.practicum.shareit.user.dto.UserDto postUser(@Validated({Create.class}) @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PatchMapping("/{userId}")
    public ru.practicum.shareit.user.dto.UserDto patchUser(@Validated({Update.class}) @PathVariable long userId,
                                                           @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @GetMapping()
    public List<ru.practicum.shareit.user.dto.UserDto> getAllUsers() {
        return userService.getAllUsers().collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ru.practicum.shareit.user.dto.UserDto getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}

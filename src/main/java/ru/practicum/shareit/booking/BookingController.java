package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingReturnDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingReturnDto addBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                       @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingReturnDto patchBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                         @PathVariable Long bookingId, @RequestParam boolean approved) {
        return bookingService.patchBooking(bookingId, userId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingReturnDto getBooking(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        return bookingService.getBooking(bookingId, userId);
    }

    @GetMapping
    public List<BookingReturnDto> getUserBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                  @RequestParam(required = false, defaultValue = "ALL") String state,
                                                  @PositiveOrZero @RequestParam(name = "from", defaultValue = "0")
                                                          Integer from,
                                                  @Positive @RequestParam(name = "size", defaultValue = "10")
                                                          Integer size) {
        return bookingService.getUserBookingList(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<BookingReturnDto> getOwnerBookings(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                   @RequestParam(required = false, defaultValue = "ALL") String state,
                                                   @PositiveOrZero @RequestParam(name = "from", defaultValue = "0")
                                                           Integer from,
                                                   @Positive @RequestParam(name = "size", defaultValue = "10")
                                                           Integer size) {
        return bookingService.getOwnerBookingList(userId, state, from, size);
    }

}

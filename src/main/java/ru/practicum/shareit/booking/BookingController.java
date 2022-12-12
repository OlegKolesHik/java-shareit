package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingReturnDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public BookingReturnDto getBooking(@RequestHeader("X-Sharer-User-Id") Long userID, @PathVariable Long bookingId) {
        return bookingService.getBooking(bookingId, userID);
    }

    @GetMapping
    public List<BookingReturnDto> getUserBookings(@RequestHeader("X-Sharer-User-Id") Long userID,
                                                  @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getUserBookingList(userID, state);
    }

    @GetMapping("/owner")
    public List<BookingReturnDto> getOwnerBookings(@RequestHeader("X-Sharer-User-Id") Long userID,
                                                   @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getOwnerBookingList(userID, state);
    }
}

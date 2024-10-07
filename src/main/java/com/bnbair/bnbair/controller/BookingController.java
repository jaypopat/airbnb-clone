package com.bnbair.bnbair.controller;

import com.bnbair.bnbair.domain.Booking;
import com.bnbair.bnbair.domain.BookingDto;
import com.bnbair.bnbair.domain.BookingStatus;
import com.bnbair.bnbair.exception.BookingNotFoundException;
import com.bnbair.bnbair.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        try {
            Booking booking = bookingService.getBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (BookingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/findByStatus")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@RequestParam String status) {
        try {
            BookingStatus bookingStatus = BookingStatus.valueOf(status);
            List<Booking> bookings = bookingService.getAllBookingsWithStatus(bookingStatus);
            return ResponseEntity.ok(bookings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto) {
        try {
            Booking newBooking = bookingService.createBooking(bookingDto);
            return ResponseEntity.ok(newBooking);
        } catch (Exception e) {
            // TODO: Return more specific error messages
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Booking updatedBooking = bookingService.updateBooking(id, bookingDto);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/properties/{propertyId}/between")
    public ResponseEntity<List<Booking>> getBookingsForPropertyBetweenDates(
            @PathVariable Long propertyId,
            @RequestParam @DateTimeFormat(pattern = "ddMMyyyy") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "ddMMyyyy") LocalDate endDate) {
        List<Booking> bookings = bookingService.getBookingsForPropertyBetweenDates(propertyId, startDate, endDate);
        return ResponseEntity.ok(bookings);
    }
}

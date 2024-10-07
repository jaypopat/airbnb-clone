package com.bnbair.bnbair.service;

import com.bnbair.bnbair.domain.*;
import com.bnbair.bnbair.exception.BookingNotFoundException;
import com.bnbair.bnbair.exception.PropertyNotFoundException;
import com.bnbair.bnbair.exception.UserNotFoundException;
import com.bnbair.bnbair.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final PropertyService propertyService;

    public BookingService(BookingRepository bookingRepository, UserService userService, PropertyService propertyService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.propertyService = propertyService;
    }

    public List<Booking> getAllBookings() {
        return (List<Booking>) bookingRepository.findAll();
    }

    public Booking getBookingById(Long bookingId) throws BookingNotFoundException {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
    }

    public List<Booking> getAllBookingsWithStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }

    public Booking createBooking(BookingDto bookingDto) throws UserNotFoundException, PropertyNotFoundException {
        User booker = userService.getUserById(bookingDto.getBookerId());
        Property property = propertyService.getPropertyById(bookingDto.getPropertyId());
        long stayDuration = ChronoUnit.DAYS.between(bookingDto.getStartDate(), bookingDto.getEndDate());
        float pricePerNight = property.getPricePerNight();

        Booking newBooking = new Booking(booker, property, bookingDto.getStartDate(), bookingDto.getEndDate(), stayDuration * pricePerNight, bookingDto.getHeadCount());

        return bookingRepository.save(newBooking);
    }

    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    // Note: For a booking, some fields simply do not make sense to update.
    // It does not make sense to update the User of a booking, or the property. In this scenario, the user should
    // cancel their booking and create a new one. The allowed updates would be startDate, endDate, headCount and status.
    // The paymentAmount should be updated accordingly.
    public Booking updateBooking(Long bookingId, BookingDto bookingData) throws BookingNotFoundException {
        Booking originalBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        // TODO: Add validation here (e.g. check for start date in the past)
        if (bookingData.getStartDate() != null) {
            originalBooking.setStartDate(bookingData.getStartDate());
        }
        if (bookingData.getEndDate() != null) {
            originalBooking.setEndDate(bookingData.getEndDate());
        }
        if (bookingData.getStatus() != null) {
            originalBooking.setStatus(bookingData.getStatus());
        }
        if (bookingData.getHeadCount() != 0) {
            originalBooking.setHeadCount(bookingData.getHeadCount());
        }
        return bookingRepository.save(originalBooking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> getBookingsForPropertyBetweenDates(Long propertyId, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByPropertyIdBetween(propertyId, startDate, endDate);
    }
}

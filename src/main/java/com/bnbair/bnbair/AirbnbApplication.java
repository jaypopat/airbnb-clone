package com.bnbair.bnbair;

import com.bnbair.bnbair.domain.*;
import com.bnbair.bnbair.service.BookingService;
import com.bnbair.bnbair.service.PropertyService;
import com.bnbair.bnbair.service.ReviewService;
import com.bnbair.bnbair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

//import java.security.Provider;

@SpringBootApplication
public class AirbnbApplication implements CommandLineRunner {
    UserService userService;
    PropertyService propertyService;
    BookingService bookingService;
    ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(AirbnbApplication.class);

    public AirbnbApplication(UserService userService, PropertyService propertyService, BookingService bookingService,
                             ReviewService reviewService) {
        this.userService = userService;
        this.propertyService = propertyService;
        this.bookingService = bookingService;
        this.reviewService = reviewService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AirbnbApplication.class, args);

        logger.info("Application started");
    }

    @Override
    public void run(String... args) /* throws Exception */ {
        User user1 = new User("Jay", "Bomba", "j.bomba@gmail.com");
        User user2 = new User("Pat", "Reeks", "p.reeks@gmail.com");
        userService.createUser(user1);
        userService.createUser(user2);

        Property property1 = new Property(user1, "Pope House", "123 Milton Street", "Ireland",
                "The best house of them all", null, 30, AccommodationType.BARN);
        Property property2 = new Property(user2, "Seashell Shack", "15 Salthill Rd.", "Ireland",
                "Welcome to the Seashell Shack, where the waves are our lullabies and the seagulls are our nosy neighbors. This cozy beach house is so close to the water, you’ll need a snorkel just to fetch the morning paper. Here’s what you can expect:",
                null, 100, AccommodationType.BEACH_HOUSE);
        propertyService.createProperty(property1);
        propertyService.createProperty(property2);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        // Jay is booking a night at his Pope house for one day. 30 * 1 day = 30.0
        Booking booking1 = new Booking(user1, property1, today, tomorrow, 30.0f, 1);
        // Pat is staying at his beach house for 3 days
        Booking booking2 = new Booking(user2, property2, today, tomorrow.plusDays(2), property1.getPricePerNight() * 3,
                2);
        bookingService.createBooking(booking1);
        bookingService.createBooking(booking2);

        Review review1 = new Review(booking1, 10, "The best place1");
//        Review review2 = new Review(booking2, 5, "This was mid");
        reviewService.createReview(review1);
//        reviewService.createReview(review2);

        for (User user : userService.getAllUsers()) {
            logger.info(user.toString());
        }

        for (Property property : propertyService.getAllProperties()) {
            logger.info(property.toString());
        }

        for (Booking booking : bookingService.getAllBookings()) {
            logger.info(booking.toString());
        }

        for (Review review: reviewService.getAllReviews()) {
            logger.info(review.toString());
        }
    }
}

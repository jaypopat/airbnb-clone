package com.bnbair.bnbair.service;

import com.bnbair.bnbair.domain.Booking;
import com.bnbair.bnbair.domain.Review;
import com.bnbair.bnbair.domain.ReviewDto;
import com.bnbair.bnbair.exception.BookingNotFoundException;
import com.bnbair.bnbair.exception.ReviewNotFoundException;
import com.bnbair.bnbair.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final BookingService bookingService;

  private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

  public ReviewService(ReviewRepository reviewRepository, BookingService bookingService) {
    this.reviewRepository = reviewRepository;
    this.bookingService = bookingService;
  }

  public List<Review> getAllReviews() {
    return (List<Review>) reviewRepository.findAll();
  }

  public Review getReviewById(Long id) throws ReviewNotFoundException {
    return reviewRepository.findById(id)
            .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
  }

  public List<Review> getAllReviewsWithScoreBetween(int start, int end) {
    return reviewRepository.findAllByScoreBetween(start, end);
  }

  public Review createReview(ReviewDto reviewDto) {
    try {
      Booking reviewBooking = bookingService.getBookingById(reviewDto.getBookingId());
      Review review = new Review(reviewBooking, reviewDto.getScore(), reviewDto.getMessage());
      return reviewRepository.save(review);
    } catch (DataIntegrityViolationException e) {
      logger.error("Review already exists");
      return null;
    } catch (BookingNotFoundException e) {
      logger.error("Booking not found");
      return null;
    }
  }

  public void createReview(Review review) {
    reviewRepository.save(review);
  }

  public Review updateReview(Long id, ReviewDto reviewDto) throws ReviewNotFoundException {
    Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

    // TODO: Add validation
    review.setScore(reviewDto.getScore());
    if (reviewDto.getMessage() != null && !reviewDto.getMessage().isEmpty()) {
      review.setMessage(reviewDto.getMessage());
    }

    return reviewRepository.save(review);
  }

  public void deleteReview(Long id) {
    reviewRepository.deleteById(id);
  }
}

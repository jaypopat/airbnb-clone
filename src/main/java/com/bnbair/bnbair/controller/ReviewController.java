package com.bnbair.bnbair.controller;

import com.bnbair.bnbair.domain.Review;
import com.bnbair.bnbair.domain.ReviewDto;
import com.bnbair.bnbair.exception.ReviewNotFoundException;
import com.bnbair.bnbair.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<List<Review>> getReviews() {
    List<Review> reviews = reviewService.getAllReviews();
    return ResponseEntity.ok(reviews);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
    try {
      Review review = reviewService.getReviewById(id);
      return ResponseEntity.ok(review);
    } catch (ReviewNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/search/findAllByScoreBetween")
  public ResponseEntity<List<Review>> findAllByScoreBetween(@RequestParam int start, @RequestParam int end) {
      return ResponseEntity.ok(reviewService.getAllReviewsWithScoreBetween(start, end));
  }

  @PostMapping
  public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto) {
    Review review = reviewService.createReview(reviewDto);
    // Return bad request if review was already created
    if (review == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(review);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
    try {
      Review updatedReview = reviewService.updateReview(id, reviewDto);
      return ResponseEntity.ok(updatedReview);
    } catch (ReviewNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
    reviewService.deleteReview(id);
    return ResponseEntity.noContent().build();
  }
}

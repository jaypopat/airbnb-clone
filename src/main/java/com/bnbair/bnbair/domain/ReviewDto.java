package com.bnbair.bnbair.domain;

import lombok.Getter;

@Getter
public class ReviewDto {
  private Long id;
  private final Long bookingId;
  private final Integer score;
  private final String message;

  public ReviewDto(Long bookingId, int score, String message) {
    this.bookingId = bookingId;
    this.score = score;
    this.message = message;
  }
}

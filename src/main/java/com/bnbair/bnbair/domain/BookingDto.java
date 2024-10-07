package com.bnbair.bnbair.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookingDto {
    Long bookerId;
    Long propertyId;
    LocalDate startDate, endDate;
    int headCount;
    BookingStatus status;
}

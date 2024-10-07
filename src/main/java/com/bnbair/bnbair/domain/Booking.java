package com.bnbair.bnbair.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private User booker;

  @ManyToOne(fetch = FetchType.EAGER)
  private Property property;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "booking")
  private Review review;

  private LocalDate startDate, endDate;
  private float paymentAmount;
  private int headCount;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;


  public Booking(User booker, Property property, LocalDate startDate, LocalDate endDate, float paymentAmount, int headCount) {
    this.booker = booker;
    this.property = property;
    this.startDate = startDate;
    this.endDate = endDate;
    this.paymentAmount = paymentAmount;
    this.headCount = headCount;
    this.status = BookingStatus.PENDING;
  }

  @Override
  public String toString() {
    return "Booking{" +
            "id=" + id +
            ", booker=" + booker.toString() +
            ", property=" + property +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", paymentAmount=" + paymentAmount +
            ", headCount=" + headCount +
            ", status=" + status +
            '}';
  }
}
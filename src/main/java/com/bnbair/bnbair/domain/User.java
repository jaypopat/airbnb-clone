package com.bnbair.bnbair.domain;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table") // Must keep this name as 'user' is reserved in H2
public class User {

  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "First name is required")
  @Column(nullable = false)
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Column(nullable = false)
  private String lastName;

  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is required")
  @Column(nullable = false, unique = true)
  private String email;

//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @JsonIgnore
//    private String password;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "booker")
  private List<Booking> bookings;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private List<Property> properties;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
  private List<Review> reviews;

  @Column(nullable = false, updatable = false)
  @Setter(AccessLevel.NONE)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column
  private Float ownerRating;

  @Column
  private Float guestRating;

  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    ownerRating = null;
    guestRating = null;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", createdAt=" + createdAt +
            ", ownerRating=" + ownerRating +
            ", guestRating=" + guestRating +
            '}';
  }
}

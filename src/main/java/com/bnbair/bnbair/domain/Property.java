package com.bnbair.bnbair.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property {

    @Id
    @Setter(value = AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @NotBlank(message = "Property name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Property address is required")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Property country is required")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Property description is required")
    @Column(nullable = false)
    private String description;

    private Float rating;

    @Column(nullable = false)
    private float pricePerNight;

    @Column(nullable = false, updatable = false)
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Review> reviews;

    public Property(User owner, String name, String address, String country, String description, Float rating,
            float pricePerNight, AccommodationType type) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.country = country;
        this.description = description;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", owner=" + owner.getId() +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", pricePerNight=" + pricePerNight +
                ", createdAt=" + createdAt +
                ", type=" + type +
                '}';
    }
}

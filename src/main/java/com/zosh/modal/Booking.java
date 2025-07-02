package com.zosh.modal;

import com.zosh.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_seq_gen")
    @SequenceGenerator(name = "bookings_seq_gen", sequenceName = "bookings_seq", allocationSize = 1)
    private Long id;

    private Long salonId;

    private Long customerId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ElementCollection
    @CollectionTable(name = "booking_service_ids", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "service_id")
    private Set<Long> serviceIds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    private int totalPrice;
}
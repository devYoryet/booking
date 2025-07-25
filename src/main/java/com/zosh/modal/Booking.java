package com.zosh.modal;

import com.zosh.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_seq_gen")
    @SequenceGenerator(name = "bookings_seq_gen", sequenceName = "bookings_seq", allocationSize = 1)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "salon_id", nullable = false)
    private Long salonId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private BookingStatus status = BookingStatus.PENDING;

    // Para mapear la relación many-to-many con services
    @ElementCollection
    @CollectionTable(
        name = "booking_service_ids",
        joinColumns = @JoinColumn(name = "booking_id")
    )
    @Column(name = "service_ids")
    private Set<Long> serviceIds;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

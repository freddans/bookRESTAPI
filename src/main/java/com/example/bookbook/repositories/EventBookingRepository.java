package com.example.bookbook.repositories;

import com.example.bookbook.entities.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
}

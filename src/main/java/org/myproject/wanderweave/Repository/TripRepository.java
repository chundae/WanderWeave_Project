package org.myproject.wanderweave.Repository;

import org.myproject.wanderweave.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findByUserId(Long userId);

    Trip findByTripId(Long tripId);

    List<Trip> findAllByUserId(Long userId);

    void deleteByTripId(Long tripId);
}

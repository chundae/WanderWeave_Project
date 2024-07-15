package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService_ITF {
    Trip addTrip(Trip trip);


//    Optional<Trip> loadAllTrip(Long userId);

    Optional<Trip> viewTrip(Long tripId);

    List<Trip> viewAllTrips(Long userId);

    void deleteTrip(Long tripId);

    void updateTrip(Trip trip, Long tripId);
}

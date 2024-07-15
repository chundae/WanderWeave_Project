package org.myproject.wanderweave.Repository;

import org.myproject.wanderweave.Domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByTripId(Long tripId);

    void deleteByActivityId(Long ActivityId);

}

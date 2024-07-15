package org.myproject.wanderweave.Repository;

import org.myproject.wanderweave.Domain.SharedTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<SharedTrip, Integer> {
}

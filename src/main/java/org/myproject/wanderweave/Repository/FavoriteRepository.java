package org.myproject.wanderweave.Repository;

import org.myproject.wanderweave.Domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}

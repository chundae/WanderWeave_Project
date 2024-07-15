package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.Favorite;
import org.myproject.wanderweave.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService implements FavoriteService_ITF{

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void addActivity(Favorite favorite) {
        favoriteRepository.save(favorite);
    }
}

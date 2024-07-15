package org.myproject.wanderweave.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FavoriteId")
    private Long favoriteId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "ActivityId")
    private Long activityId;

    public Favorite(Long userId, Long activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }
    public Favorite() {

    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}

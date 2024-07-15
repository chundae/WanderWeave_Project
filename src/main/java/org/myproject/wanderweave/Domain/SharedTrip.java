package org.myproject.wanderweave.Domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "SharedTrip")
public class SharedTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SharedId")
    private Long sharedId;

    @Column(name = "TripId")
    private Long tripId;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getSharedId() {
        return sharedId;
    }

    public void setSharedId(Long sharedId) {
        this.sharedId = sharedId;
    }
}

package org.myproject.wanderweave.Domain;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ActivityId")
    private Long activityId;

    @Column(name = "ActivityName")
    private String activityName;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE) //타입 지정
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 포멧지정
    private Date date;

    @Column(name = "Time")
    private String time;
    @Column(name = "location")
    private String location;

    @Column(name = "latitude", nullable = true)
    private double latitude;
    @Column(name = "longitude", nullable = true)
    private double longitude;

    @Column(name = "description")
    private String description;

    @Column(name = "TripId")
    private Long tripId;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}

package org.myproject.wanderweave.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Comment {

    @Id
    private Long commentId;
    private String content;
    private Long userId;
    private Long TripId;
    private Timestamp create_at;
}

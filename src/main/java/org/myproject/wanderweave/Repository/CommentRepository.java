package org.myproject.wanderweave.Repository;

import org.myproject.wanderweave.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}

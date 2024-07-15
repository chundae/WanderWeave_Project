package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CommentService_ITF{

    @Autowired
    private CommentRepository commentRepository;
}

package org.myproject.wanderweave.Controller;

import org.myproject.wanderweave.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
}

package com.videorunner.controllers;

import com.videorunner.models.Comment;
import com.videorunner.models.User;
import com.videorunner.models.Video;
import com.videorunner.services.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add-comment/{video}")
    public String addComment(@AuthenticationPrincipal User user,
                             @PathVariable Video video,
                             @RequestParam String text)
    {

        commentService.saveComment(new Comment(text,user,video));

        return "redirect:/video/"+video.getId();
    }
}

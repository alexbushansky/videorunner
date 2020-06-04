package com.videorunner.services;


import com.videorunner.models.Comment;
import com.videorunner.repo.ICommentRepo;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final ICommentRepo commentRepo;


    public CommentService(ICommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }


    public void saveComment(Comment comment)
    {
        commentRepo.save(comment);
    }

}

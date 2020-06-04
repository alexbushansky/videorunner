package com.videorunner.repo;

import com.videorunner.models.Comment;
import com.videorunner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
}

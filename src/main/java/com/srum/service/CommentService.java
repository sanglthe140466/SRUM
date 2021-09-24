package com.srum.service;

import com.srum.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentByTraineeId(Long id);

    void addComment(Comment comment);
}

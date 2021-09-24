package com.srum.service.impl;

import com.srum.entity.Comment;
import com.srum.repository.CommentRepository;
import com.srum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getAllCommentByTraineeId(Long id) {
        return commentRepository.getCommentByTraineeIdOrderByIdDesc(id);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCreatedDate(new Date());
        commentRepository.save(comment);
    }
}

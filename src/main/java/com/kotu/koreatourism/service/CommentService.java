package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Comment;

import java.util.List;

public interface CommentService {

    public void saveComment(Comment comment);

    public void deleteComment(int commentId);

    public List<Comment> findAllComment(int bid);

    public void updateComment(Comment comment);
}

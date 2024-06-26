package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Comment;
import com.kotu.koreatourism.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    public void saveComment(CommentDTO commentDTO);

    public void deleteComment(int commentId, int bid);

    public List<CommentDTO> findAllComment(int bid);

    public int updateComment(CommentDTO commentDTO);

    public CommentDTO findCommentById(int commentId);
}

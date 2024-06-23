package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Comment;
import com.kotu.koreatourism.dto.CommentDTO;
import com.kotu.koreatourism.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    @Override
    public void saveComment(CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setAuthor(commentDTO.getAuthor());
        comment.setComment(commentDTO.getComment());
        comment.setBid(commentDTO.getBid());

        commentMapper.saveComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {

    }

    @Override
    public List<Comment> findAllComment(int bid) {
        return null;
    }

    @Override
    public void updateComment(Comment comment) {

    }
}

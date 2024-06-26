package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Comment;
import com.kotu.koreatourism.dto.CommentDTO;
import com.kotu.koreatourism.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        comment.setUserId(commentDTO.getUserId());

        commentMapper.saveComment(comment);
    }

    @Override
    public void deleteComment(int commentId, int bid) {
        commentMapper.deleteComment(bid, commentId);
    }

    @Override
    public List<CommentDTO> findAllComment(int bid) {

        List<Comment> allComment = commentMapper.findAllComment(bid);

        List<CommentDTO> findAllCommentToCommentDTOList = allComment.stream()
                .map( comment -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentId(comment.getCommentId());
                    commentDTO.setBid(comment.getBid());
                    commentDTO.setAuthor(comment.getAuthor());
                    commentDTO.setComment(comment.getComment());
                    commentDTO.setRegdate(comment.getRegdate());
                    commentDTO.setUserId(comment.getUserId());
                    return commentDTO;
                })
                .collect(Collectors.toList());

        return findAllCommentToCommentDTOList;
    }

    @Override
    public int updateComment(CommentDTO commentDTO) {

        Comment commentById = commentMapper.findCommentById(commentDTO.getCommentId());
        commentById.setComment(commentDTO.getComment());
        return commentMapper.updateComment(commentById);
    }

    @Override
    public CommentDTO findCommentById(int commentId) {

        Comment commentById = commentMapper.findCommentById(commentId);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(commentById.getCommentId());
        commentDTO.setAuthor(commentById.getAuthor());
        commentDTO.setBid(commentById.getBid());
        commentDTO.setRegdate(commentById.getRegdate());
        commentDTO.setComment(commentById.getComment());
        commentDTO.setUserId(commentById.getUserId());

        return commentDTO;
    }
}

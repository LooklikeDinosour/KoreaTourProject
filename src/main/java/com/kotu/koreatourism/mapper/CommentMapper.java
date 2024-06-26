package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Comment;
import com.kotu.koreatourism.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int saveComment(Comment comment);

    public void deleteComment(@Param("bid") int bid,
                              @Param("commentId") int commentId);

    public List<Comment> findAllComment(int bid);

    public int updateComment(Comment comment);

    public Comment findCommentById(int commentId);

}

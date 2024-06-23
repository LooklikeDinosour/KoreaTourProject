package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int saveComment(Comment comment);

    public void deleteComment(int commentId);

    public List<Comment> findAllComment(int bid);

    public void updateComment(Comment comment);

}

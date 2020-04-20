package com.jl.service;

import com.jl.vo.Comment;

import java.util.List;

/**
 * @author J-Lei
 * @date 2020/3/17 15:32
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComent(Comment comment);
}

package com.tusofia.myapp.service;

import java.util.ArrayList;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.model.User;

public interface CommentService {

    ArrayList<Comment> findAllByParentIdAndParentType(Long id, String type);

    void save(Comment comment);

    ArrayList<Comment> findAllUnmoderated();

    void deleteById(Long id);

    Comment getCommentById(Long id);

    Comment makeNewComment(Long parentId, User user, String parentType, String body);

    ArrayList<Comment> findAllByUser(User user);

    void saveAll(ArrayList<Comment> list);

    void changeOwner(Long id, Long newId);

}

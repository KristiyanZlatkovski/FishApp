package com.tusofia.myapp.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.model.User;


public interface CommentRep extends JpaRepository<Comment, Long> {

    ArrayList<Comment> findAllByParentIdAndParentType(Long id, String type, Sort sort);

    ArrayList<Comment> findAllByIsModerated(boolean moderated);

    ArrayList<Comment> findAllByUsers(User user);


}

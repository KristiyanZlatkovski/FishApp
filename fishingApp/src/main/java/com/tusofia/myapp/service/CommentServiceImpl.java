package com.tusofia.myapp.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Comment;
import com.tusofia.myapp.model.User;
import com.tusofia.myapp.repository.CommentRep;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
    private CommentRep commentRep;

	@Override
	public ArrayList<Comment> findAllByParentIdAndParentType(Long id, String type) {
		
		return commentRep.findAllByParentIdAndParentType(id, type,Sort.by(Sort.Direction.DESC, "date"));
	}

	@Override
	public void save(Comment comment) {
		commentRep.save(comment);
		
	}

	@Override
	public ArrayList<Comment> findAllUnmoderated() {
	
		return commentRep.findAllByIsModerated(false);
	}

	@Override
	public void deleteById(Long id) {
		commentRep.deleteById(id);
		
	}

	@Override
	public Comment getCommentById(Long id) {
		
		return commentRep.getById(id);
	}

	@Override
	public Comment makeNewComment(Long parentId, User user, String parentType, String body) {
		
		
		Comment comment=new Comment();
		 Date date= new Date();
		 comment.setDate(date);
		 comment.setParentType(parentType);
		 comment.setBody(body);
		 comment.setParentId(parentId);
		 comment.setUsers(user);
		
		
	
		return comment;
	}

	@Override
	public ArrayList<Comment> findAllByUser(User user) {
		
		return commentRep.findAllByUsers(user);
	}

	@Override
	public void saveAll(ArrayList<Comment> list) {
		commentRep.saveAll(list);
		
	}

	@Override
	public void changeOwner(Long id, Long newId) {
		User user=new User();
		User newuser=new User();
		user.setId(id);
		 newuser.setId(newId);
		
		ArrayList<Comment> currentList=findAllByUser(user);
		for (int i = 0; i < currentList.size(); i++) {
			
			currentList.get(i).setUsers(newuser);
			
			commentRep.saveAll(currentList);
	}
		
	}
	
	
}

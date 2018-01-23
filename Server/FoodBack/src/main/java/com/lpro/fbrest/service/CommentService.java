package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Comment;
import com.lpro.fbrest.api.User;
import com.lpro.fbrest.db.CommentDAO;

/**
 * Service for Comment management
 */
public abstract class CommentService {

	@CreateSqlObject
	abstract CommentDAO commentdao();
	
	/**
	 * @param comment Comment to be inserted 
	 */
	public void newComment(Comment comment) {
		if(comment.getRating() < 1 || comment.getRating() > 5) throw new WebApplicationException(400);
		try {
			commentdao().insertComment(comment.getEstablishment_id(),
									comment.getCommenter_id(),
									comment.getRating(),
									comment.getComment());
			commentdao().updateEstablishmentRating(comment.getEstablishment_id());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @param comment_id ID of comment to be deleted
	 * @param users_id ID of user
	 */
	public void deleteComment(long users_id, long comment_id) {
		Comment comment = null;
		try {
			comment = commentdao().getCommentById(comment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(comment == null) throw new WebApplicationException(404);
		if(users_id > 0 && comment.getCommenter_id() != users_id) throw new WebApplicationException(403);
		try {
			commentdao().deleteComment(comment_id);
			commentdao().updateEstablishmentRating(comment.getEstablishment_id());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @param establishment_id Id of the establishment 
	 * @return Comments made to the establishment specified
	 */
	public List<Comment> getEstablishmentComments(long establishment_id) {
		try {
			return commentdao().getEstablishmentComments(establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * @param comment_id ID of comment
	 * @return User who made comment
	 */
	public User getCommenterUser(long comment_id) {
		try {
			return commentdao().getCommenterUser(comment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * @param comment_id ID of comment
	 * @return Comment with specified id
	 */
	public Comment getCommentById(long comment_id) {
		try {
			return commentdao().getCommentById(comment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

}

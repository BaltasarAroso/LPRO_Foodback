package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Comment;
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
	 * @param comment Comment to be deleted
	 */
	public void deleteComment(Comment comment) {
		try {
			commentdao().deleteComment(comment.getId());
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

}

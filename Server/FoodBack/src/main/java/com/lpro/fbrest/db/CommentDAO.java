package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Comment;
import com.lpro.fbrest.api.User;

import com.lpro.fbrest.db.UserMapper;

/**
 * DAO for comment persistent data
 */
@RegisterMapper(CommentMapper.class)
public interface CommentDAO {
	
	/**
	 * @param establishment_id Id of the establishment
	 * @param commenter_id Id of the commenter
	 * @param rating Rating the commenter gave
	 * @param comment Opinion of the commenter
	 * @return id of the inserted comment
	 */
	@SqlUpdate("INSERT INTO comment "
			+ "VALUES (DEFAULT, :establishment_id, :commenter_id, DEFAULT, :rating, :comment)")
	@GetGeneratedKeys
	public long insertComment(@Bind("establishment_id") long establishment_id,
							@Bind("commenter_id") long commenter_id,
							@Bind("rating") int rating,
							@Bind("comment") String comment);
	
	/**
	 * @param comment_id If of the comment to be deleted
	 * 
	 * Deletes a comment
	 */
	@SqlUpdate("DELETE FROM comment "
			+ "WHERE id = :comment_id")
	public void deleteComment(@Bind("id") long comment_id);
	
	/**
	 * @param establishment_id Id of the establishment
	 * @return List of comments given to the establishment specified
	 */
	@SqlQuery("SELECT * "
			+ "FROM comment "
			+ "WHERE establishment_id = :establishment_id")
	public List<Comment> getEstablishmentComments(@Bind("establishment_id") long establishment_id);

	/**
	 * @param establishment_id ID of establishment to update rating
	 */
	@SqlUpdate("UPDATE establishment "
			+ "SET rating = (SELECT AVG(rating) FROM comment WHERE establishment_id = :id) "
			+ "WHERE id = :id")
	public void updateEstablishmentRating(@Bind("id") long establishment_id);

	/**
	 * @param comment_id ID of comment
	 * @return User who made comment
	 */
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium, zone, city "
			+ "FROM users JOIN credential ON users_id = users.id "
			+ "WHERE users.id = (SELECT commenter_id FROM comment WHERE id = :id)")
	@RegisterMapper(UserMapper.class)
	public User getCommenterUser(@Bind("id") long comment_id);

}

package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Answer;

/**
 * @author Daniel
 *
 */
@RegisterMapper(AnswerMapper.class)
public interface AnswerDAO {

	/**
	 * @param comment_id ID of comment 
	 * @param answer Answer
	 * @return ID of inserted answer
	 */
	@SqlUpdate("INSERT INTO comment_answer "
			+ "VALUES (DEFAULT, :comment_id, :answer, DEFAULT)")
	@GetGeneratedKeys
	public long insertAnswer(@Bind("comment_id") long comment_id, @Bind("answer") String answer);
	
	/**
	 * @param comment_id ID of comment 
	 * @return Answer to the specified comment
	 */
	@SqlQuery("SELECT * "
			+ "FROM comment_answer "
			+ "WHERE comment_id = :comment_id")
	public Answer getAnswerByCommentId(@Bind("comment_id") long comment_id);
	
	/**
	 * @param id ID of Answer
	 * @return Answer with specified answer
	 */
	@SqlQuery("SELECT * "
			+ "FROM comment_answer "
			+ "WHERE id = :id")
	public Answer getAnswerById(@Bind("id") long id);
	
	/**
	 * @param establishment_id ID of establishment
	 * @return List of answers made by the specified establishment
	 */
	@SqlQuery("SELECT comment_answer.* "
			+ "FROM comment_answer JOIN comment ON comment_id = comment.id "
			+ "WHERE establishment_id = :establishment_id")
	public List<Answer> getAllAnswersByEstablishmentId(@Bind("establishment_id") long establishment_id);
	
	/**
	 * @param id ID of answer
	 */
	@SqlUpdate("DELETE FROM comment_answer "
			+ "WHERE id = :id")
	public void deleteAnswer(@Bind("id") long id);
	
	/**
	 * @param id ID of answer
	 * @param answer Answer to be inserted 
	 */
	@SqlUpdate("UPDATE comment_answer "
			+ "SET answer = :answer, time_posted = now() "
			+ "WHERE id = :id")
	public void updateAnswer(@Bind("id") long id, @Bind("answer") String answer);
	
	
	
	/**
	 * @param comment_id ID of comment
	 * @return ID of establishment
	 */
	@SqlQuery("SELECT establishment_id "
			+ "FROM comment "
			+ "WHERE id = :id")
	public long getEstablishmentIdByCommentId(@Bind("id") long comment_id);
	
}

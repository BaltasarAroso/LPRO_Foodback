package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Answer;
import com.lpro.fbrest.db.AnswerDAO;

/**
 * @author Daniel
 *
 */
public abstract class AnswerService {
	
	@CreateSqlObject
	abstract AnswerDAO answerDao();
	
	/**
	 * @param answer Answer to be stored
	 */
	public void newAnswer(Answer answer) {
		try {
			answerDao().insertAnswer(answer.getComment_id(), answer.getAnswer());
		} catch(UnableToExecuteStatementException e) {
			throw new WebApplicationException(400);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @param comment_id ID of comment which answer is going to be searched
	 * @return Answer if found
	 */
	public Answer getAnswerByCommentId(long comment_id) {
		Answer answer;
		try {
			answer = answerDao().getAnswerByCommentId(comment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(answer == null) throw new WebApplicationException(404);
		return answer;
	}
	
	/**
	 * @param establishment_id ID of establishment whict answers are going to be searched
	 * @return List of found answers
	 */
	public List<Answer> getAllAnswersByEstablishmentId(long establishment_id){
		List<Answer> answer;
		try {
			answer = answerDao().getAllAnswersByEstablishmentId(establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(answer == null) throw new WebApplicationException(404);
		if(answer.isEmpty()) throw new WebApplicationException(404);
		return answer;
	}

	
	/**
	 * @param comment_id ID of comment
	 * @param establishment_id ID of establishment
	 * @return If the logged client is owner or not
	 */
	public boolean checkIfIsOwner(long comment_id, long establishment_id) {
		if(establishment_id == answerDao().getEstablishmentIdByCommentId(comment_id)) return true;
		return false;
	}
}

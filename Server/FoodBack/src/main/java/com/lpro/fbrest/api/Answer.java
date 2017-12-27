package com.lpro.fbrest.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Daniel
 *
 */
public class Answer {
	
	/**
	 *  Id of Answer 
	 */
	@JsonProperty
	private long id;
	
	/**
	 *  Id of comment
	 */
	@JsonProperty
	private long comment_id;
	
	/**
	 * Answer to Comment
	 */
	@JsonProperty
	private String answer;
	
	/**
	 * Time the answers was added to db
	 */
	@JsonProperty
	private Timestamp time_posted;
	
	/**
	 *  Constructor for Jackson
	 */
	public Answer() {
		super();
	}

	/**
	 * @param id ID of answer
	 * @param comment_id ID of comment
	 * @param answer Answer to Comment
	 * @param time_posted Time it was added to db
	 */
	public Answer(long id, long comment_id, String answer, Timestamp time_posted) {
		super();
		this.id = id;
		this.comment_id = comment_id;
		this.answer = answer;
		this.time_posted = time_posted;
	}

	/**
	 * @return ID of answer
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id ID of answer
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return ID of comment
	 */
	public long getComment_id() {
		return comment_id;
	}

	/**
	 * @param comment_id ID of comment
	 */
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	/**
	 * @return Answer to comment
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer Answer to comment
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return Time the answer was added to db
	 */
	public Timestamp getTime_posted() {
		return time_posted;
	}

	/**
	 * @param time_posted Time the answer was added to db
	 */
	public void setTime_posted(Timestamp time_posted) {
		this.time_posted = time_posted;
	}
}

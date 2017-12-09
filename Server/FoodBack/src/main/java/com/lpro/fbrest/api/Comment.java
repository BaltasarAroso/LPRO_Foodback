package com.lpro.fbrest.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
	
	@JsonProperty
	private long id;
	
	@JsonProperty
	private int establishment_id;
	
	@JsonProperty
	private int commenter_id;
	
	@JsonProperty
	private Timestamp time_posted;
	
	@JsonProperty
	private int rating;
	
	@JsonProperty
	private String comment;
	
	public Comment() {
		super();
	}

	public Comment(long id, int establishment_id, int commenter_id, Timestamp time_posted, int rating, String comment) {
		super();
		this.id = id;
		this.establishment_id = establishment_id;
		this.commenter_id = commenter_id;
		this.time_posted = time_posted;
		this.rating = rating;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEstablishment_id() {
		return establishment_id;
	}

	public void setEstablishment_id(int establishment_id) {
		this.establishment_id = establishment_id;
	}

	public int getCommenter_id() {
		return commenter_id;
	}

	public void setCommenter_id(int commenter_id) {
		this.commenter_id = commenter_id;
	}

	public Timestamp getTime_posted() {
		return time_posted;
	}

	public void setTime_posted(Timestamp time_posted) {
		this.time_posted = time_posted;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Comment)) return false;
		
		Comment comment = (Comment)o;
		if(this.id == comment.getId() &&
				this.establishment_id == comment.getEstablishment_id() &&
				this.commenter_id == comment.getCommenter_id() &&
				this.time_posted.equals(comment.getTime_posted()) &&
				this.rating == comment.getRating() &&
				this.comment.equals(comment.getComment()))
			return true;
		else return false;
	}

}

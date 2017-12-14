package com.foodback.foodback.logic;

import java.sql.Timestamp;

/**
 * Created by Foodback on 14/12/2017.
 */

public class Comment {

    /**
     *  comment id
     */
    private long id;

    /**
     *  id of the establishment that is being commented
     */
    private int establishment_id;

    /**
     *  id of the user that is commenting
     */
    private int commenter_id;

    /**
     *  Time of comment creation
     */
    private Timestamp time_posted;

    /**
     *  Score the user gave to the establishment
     */
    private int rating;

    /**
     *  Written opinion of the user
     */
    private String comment;

    /**
     * @param id id of comment
     * @param establishment_id id of establishment
     * @param commenter_id if of commenter
     * @param time_posted time posted
     * @param rating rating the user gave
     * @param comment opinion of the user
     *
     * Constructor for one comment
     */
    public Comment(long id, int establishment_id, int commenter_id, Timestamp time_posted, int rating, String comment) {
        this.id = id;
        this.establishment_id = establishment_id;
        this.commenter_id = commenter_id;
        this.time_posted = time_posted;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * @return comment id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id comment id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return establishment id
     */
    public int getEstablishment_id() {
        return establishment_id;
    }

    /**
     * @param establishment_id establishment id
     */
    public void setEstablishment_id(int establishment_id) {
        this.establishment_id = establishment_id;
    }

    /**
     * @return commenter id
     */
    public int getCommenter_id() {
        return commenter_id;
    }

    /**
     * @param commenter_id commenter id
     */
    public void setCommenter_id(int commenter_id) {
        this.commenter_id = commenter_id;
    }

    /**
     * @return time of comment creation
     */
    public Timestamp getTime_posted() {
        return time_posted;
    }

    /**
     * @param time_posted time of comment creation
     */
    public void setTime_posted(Timestamp time_posted) {
        this.time_posted = time_posted;
    }

    /**
     * @return score the user gave the establishment
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating The score the user gave the establishment
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return written opinion
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment written opinion
     */
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

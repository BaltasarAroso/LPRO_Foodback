package com.foodback.foodback.logic;

import java.sql.Timestamp;

/**
 * Created by FoodBack.
 */

public class Featured {

    /**
     * ID of the feature
     */
    private long id;

    /**
     * id of meal
     */
    private long meal_id;

    /**
     * Date
     */
    private Timestamp added_date;

    /**
     *  Duration
     */
    private int duration;

    /**
     * Constructor of Jackson
     */
    public Featured() {
        super();
    }

    /**
     * @param meal_id of a feature
     * @param added_date of a feature
     * @param duration of a feature
     */
    public Featured(long meal_id, Timestamp added_date, int duration) {
        super();
        this.id = 0;
        this.meal_id = meal_id;
        this.added_date = added_date;
        this.duration = duration;
    }

    /**
     * @param id of a feature
     * @param meal_id of a feature
     * @param added_date of a feature
     * @param duration of a feature
     */
    public Featured(long id, long meal_id, Timestamp added_date, int duration) {
        super();
        this.id = id;
        this.meal_id = meal_id;
        this.added_date = added_date;
        this.duration = duration;
    }

    /**
     * @return id of a feature
     */
    public long getId() {
        return id;
    }

    /**
     * @param id of a feature
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return meal id of a feature
     */
    public long getMeal_id() {
        return meal_id;
    }

    /**
     * @param meal_id of a feature
     */
    public void setMeal_id(long meal_id) {
        this.meal_id = meal_id;
    }

    /**
     * @return date added of a feature
     */
    public Timestamp getAdded_date() {
        return added_date;
    }

    /**
     * @param added_date of a feature
     */
    public void setAdded_date(Timestamp added_date) {
        this.added_date = added_date;
    }

    /**
     * @return duration of a feature
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration of a feature
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Featured)) return false;

        Featured featured = (Featured)o;
        if(this.id == featured.getId() &&
                this.meal_id == featured.getMeal_id() &&
                this.added_date.equals(featured.getAdded_date()) &&
                this.duration == featured.getDuration())
            return true;
        else return false;
    }

}

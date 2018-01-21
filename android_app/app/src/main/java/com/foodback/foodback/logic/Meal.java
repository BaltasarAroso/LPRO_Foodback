package com.foodback.foodback.logic;

import java.math.BigDecimal;

/**
 * Created by FoodBack.
 */

public class Meal {

    /**
     * Meal's ID
     */
    private long id;

    /**
     * Meal's name
     */
    private String meal;

    /**
     * Meal's price
     */
    private double price;

    /**
     * ID of establishment
     */
    private long establishment_id;

    /**
     * Constructor for Jackson
     */
    public Meal() {
        super();
    }


    /**
     * @param id Meal's ID
     * @param meal Meal's name
     * @param price Meal's price
     * @param establishment_id ID of establishment
     */
    public Meal(long id, String meal, double price, long establishment_id) {
        super();
        this.id = id;
        this.meal = meal;
        this.price = price;
        this.establishment_id = establishment_id;
    }

    /**
     * @return Meal's ID
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Meal's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Meal's name
     */
    public String getMeal() {
        return meal;
    }

    /**
     * @param meal Meal's name
     */
    public void setMeal(String meal) {
        this.meal = meal;
    }

    /**
     * @return Meal's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Meal's price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return ID of establishment
     */
    public long getEstablishment_id() {
        return establishment_id;
    }

    /**
     * @param establishment_id ID of establishment
     */
    public void setEstablishment_id(long establishment_id) {
        this.establishment_id = establishment_id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Meal)) return false;

        Meal meal = (Meal)o;
        if(this.id == meal.getId() &&
                this.meal.equals(meal.getMeal()) &&
                this.price == meal.getPrice() &&
                this.establishment_id == meal.getEstablishment_id())
            return true;
        else return false;
    }

}

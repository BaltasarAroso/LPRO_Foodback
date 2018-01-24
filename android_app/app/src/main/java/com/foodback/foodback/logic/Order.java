package com.foodback.foodback.logic;

import java.util.List;

/**
 * Created by FoodBack.
 */

public class Order {

    /**
     * Id of the order
     */
    private long id;

    /**
     * List of the meals in a order
     */
    private List<OrderMeal> meals;

    /**
     * User Id
     */
    private long user_id;

    /**
     * State of the order
     */
    private String state;

    /**
     * @param id of a order
     * @param meals of a order
     * @param user_id of a order
     * @param state of a order
     */
    public Order(long id, List<OrderMeal> meals, long user_id, String state) {
        super();
        this.id = id;
        this.meals = meals;
        this.user_id = user_id;
        this.state = state;
    }

    /**
     * @return id of a order
     */
    public long getId() {
        return id;
    }

    /**
     * @param id of a order
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return meals of a order
     */
    public List<OrderMeal> getMeals() {
        return meals;
    }

    /**
     * @param meals of a order
     */
    public void setMeals(List<OrderMeal> meals) {
        this.meals = meals;
    }

    /**
     * @return user id that do the order
     */
    public long getUser_id() {
        return user_id;
    }

    /**
     * @param user_id that do the order
     */
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    /**
     * @return state of the order
     */
    public String getState() {
        return state;
    }

    /**
     * @param state of a order
     */
    public void setState(String state) {
        this.state = state;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Order)) return false;

        Order order = (Order)o;
        if(this.id == order.getId() &&
                this.meals.equals(order.getMeals()) &&
                this.user_id == order.getUser_id() &&
                this.state.equals(order.getState()))
            return true;
        else return false;
    }

}

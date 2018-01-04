package com.foodback.foodback.logic;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FoodBack.
 */

public class Category {

    /**
     * ID of category
     */
    private Integer id;

    /**
     * Name of category
     */
    @SerializedName("category")
    private String name;

    /**
     * @param id ID of category
     * @param name Name of category
     */
    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * @return ID of category
     */
    public int getId() {
        return id;
    }

    /**
     * @param id ID of category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Name of category
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name of category
     */
    public void setName(String name) {
        this.name = name;
    }
}

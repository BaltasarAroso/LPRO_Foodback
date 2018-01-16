package com.foodback.foodback.logic;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Foodback.
 */

public class Establishment {

    /**
     * Establishment id
     */
    private Integer id;

    /**
     * Establishment's name
     */
    private String name;

    /**
     * Establishment's category
     */
    private int category_id;

    /**
     * Establishment's address
     */
    private String address;

    /**
     * Zone of the establishment
     */
    private String zone;

    /**
     * City of the establishment
     */
    private String city;

    /**
     * Email of the establishment
     */
    private String email;

    /**
     * Contact of the establishment
     */
    private String contact;

    /**
     * Meal's average price in this establishment
     */
    private Integer avg_price;

    /**
     * Part 1 of the establishment's schedule
     */
    private String schedule1;

    /**
     * Part 2 of the establishment's schedule
     */
    private String schedule2;

    /**
     * username of the establishment's app manager
     */
    private String username;

    /**
     * password of the establishment's app manager
     */
    private String password;

    /**
     * establishment has delivery service in this app or not
     */
    private Boolean delivery;

    /**
     *
     * @param name
     * @param category_id
     * @param address
     * @param zone
     * @param city
     * @param email
     * @param contact
     * @param avg_price
     * @param schedule1
     * @param schedule2
     * @param username
     * @param password
     * @param delivery
     *
     * Constructor
     */
    public Establishment(String name, int category_id, String address, String zone, String city, String email,
                         String contact, int avg_price, String schedule1, String schedule2, String username, String password, boolean delivery) {
        this.id = null;
        this.name = name;
        this.category_id = category_id;
        this.address = address;
        this.zone = zone;
        this.city = city;
        this.email = email;
        this.contact = contact;
        this.avg_price = avg_price;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.username = username;
        this.password = password;
        this.delivery = delivery;
    }

    /**
     *
     * @param id
     * @param name
     * @param category_id
     * @param address
     * @param zone
     * @param city
     * @param email
     * @param contact
     * @param avg_price
     * @param schedule1
     * @param schedule2
     * @param username
     * @param password
     * @param delivery
     *
     * Constructor
     */
    public Establishment(int id, String name, int category_id, String address, String zone, String city, String email,
                         String contact, int avg_price, String schedule1, String schedule2, String username, String password, boolean delivery) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.address = address;
        this.zone = zone;
        this.city = city;
        this.email = email;
        this.contact = contact;
        this.avg_price = avg_price;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.username = username;
        this.password = password;
        this.delivery = delivery;
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return establishment's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return establishment's category
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     *
     * @param category_id
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    /**
     *
     * @return establishment's address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return establishment's zone
     */
    public String getZone() {
        return zone;
    }

    /**
     *
     * @param zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     *
     * @return establishment's city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return establishment's email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return establishment's contact
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return meal's average price in this establishment
     */
    public int getAvg_price() {
        return avg_price;
    }

    /**
     *
     * @param avg_price
     */
    public void setAvg_price(int avg_price) {
        this.avg_price = avg_price;
    }

    /**
     *
     * @return establishment's schedule part 1
     */
    public String getSchedule1() {
        return schedule1;
    }

    /**
     *
     * @param schedule1
     */
    public void setSchedule1(String schedule1) {
        this.schedule1 = schedule1;
    }

    /**
     *
     * @return establishment's schedule part 2
     */
    public String getSchedule2() {
        return schedule2;
    }

    /**
     *
     * @param schedule2
     */
    public void setSchedule2(String schedule2) {
        this.schedule2 = schedule2;
    }

    /**
     *
     * @return establishment's manager app username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return establishment's manager app password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return if this establishment has delivery service or not
     */
    public Boolean getDelivery() {
        return delivery;
    }

    /**
     *
     * @param delivery
     */
    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    /**
     *
     * @param o
     * @return if all elements of this establishment are equal
     *         to the elements of the establishment given by parameter
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Establishment)) return false;

        Establishment restaurante = (Establishment)o;
        return (this.id == restaurante.getId() &&
                this.name.equals(restaurante.getName()) &&
                this.category_id == restaurante.getCategory_id() &&
                this.address.equals(restaurante.getAddress()) &&
                this.zone.equals(restaurante.getZone()) &&
                this.city.equals(restaurante.getCity()) &&
                this.email.equals(restaurante.getEmail()) &&
                this.contact.equals(restaurante.getContact()) &&
                this.avg_price == restaurante.getAvg_price()) &&
                this.schedule1.equals(restaurante.getSchedule1()) &&
                this.schedule2.equals(restaurante.getSchedule2()) &&
                this.username.equals(restaurante.getUsername()) &&
                this.password.equals(restaurante.getPassword()) &&
                this.delivery.equals(restaurante.getDelivery());
    }


}
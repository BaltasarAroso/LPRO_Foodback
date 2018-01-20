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
    private String category;

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
     * establishment rating from feedback
     */
    private double rating;

    /**
     *
     * @param name Establishment's name
     * @param category Establishment's category
     * @param address Establishment's address
     * @param zone Establishment's zone
     * @param city Establishment's city
     * @param email Establishment's email
     * @param contact Establishment's contact
     * @param avg_price Establishment's average price
     * @param schedule1 Establishment's schedule part 1
     * @param schedule2 Establishment's schedule part 2
     * @param username username of the establishment's app manager
     * @param password password of the establishment's app manager
     * @param delivery establishment has delivery service in this app or not
     *
     * Constructor
     */
    public Establishment(String name, String category, String address, String zone, String city, String email,
                         String contact, int avg_price, String schedule1, String schedule2, String username, String password, boolean delivery) {
        this.id = null;
        this.name = name;
        this.category = category;
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
     * @param id Establishment's id
     * @param name Establishment's name
     * @param category Establishment's category
     * @param address Establishment's address
     * @param zone Establishment's zone
     * @param city Establishment's city
     * @param email Establishment's email
     * @param contact Establishment's contact
     * @param avg_price Establishment's average price
     * @param schedule1 Establishment's schedule part 1
     * @param schedule2 Establishment's schedule part 2
     * @param username username of the establishment's app manager
     * @param password password of the establishment's app manager
     * @param delivery establishment has delivery service in this app or not
     *
     * Constructor
     */
    public Establishment(int id, String name, String category, String address, String zone, String city, String email,
                         String contact, int avg_price, String schedule1, String schedule2, String username, String password, boolean delivery) {
        this.id = id;
        this.name = name;
        this.category = category;
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
     * @param id Establishment's id
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
     * @param name Establishment's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return establishment's category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category Establishment's category
     */
    public void setCategory(String category) {
        this.category = category;
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
     * @param address Establishment's address
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
     * @param zone Establishment's zone
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
     * @param city Establishment's city
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
     * @param email Establishment's email
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
     * @param contact Establishment's contact
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
     * @param avg_price Establishment's average price
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
     * @param schedule1 Establishment's schedule part 1
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
     * @param schedule2 Establishment's schedule part 2
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
     * @param username establishment's manager app username
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
     * @param password establishment's manager app password
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
     * @param delivery if this establishment has delivery service or not
     */
    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    /**
     * @return establishment rating or how good/popular it is
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating of an establishment
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     *
     * @param o Establishment used to compare
     * @return if all elements of this establishment are equal
     *         to the elements of the establishment given by parameter
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Establishment)) return false;

        Establishment restaurante = (Establishment)o;
        return (this.id == restaurante.getId() &&
                this.name.equals(restaurante.getName()) &&
                this.category.equals(restaurante.getCategory()) &&
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
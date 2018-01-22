package com.foodback.foodback.logic;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Foodback.
 */

public class User {

    /**
     *  user id
     */
    private Integer id;

    /**
     *  username
     */
    private String username;

    /**
     *  user password
     */
    private String password;

    /**
     *  user real name
     */
    private String name;

    /**
     *  user email
     */
    private String email;

    /**
     *  user address
     */
    private String address;

    /**
     *  user birthdate
     */
    private Date birth;

    /**
     *  is premium or not
     */
    private boolean premium;

    /**
     *  Zone of the user
     */
    private String zone;

    /**
     *  City of the user
     */
    private String city;

    /**
     * @param username username
     * @param password user password
     * @param name user real name
     * @param email user email
     * @param address user address
     * @param birth user birth date
     * @param premium is premium or not
     *
     * Constructor
     */
    public User(String username, String password, String name, String email, String address, Date birth,
                boolean premium, String zone, String city) {
        this.id = null;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birth = birth;
        this.premium = premium;
        this.zone = zone;
        this.city = city;
    }

    /**
     * @param id Id of user
     * @param username Username of user
     * @param password Password of user
     * @param name Name of user
     * @param email Email of user
     * @param address Address of user
     * @param birth Birth date of user
     * @param premium If user is premium or not
     * @param zone Zone of the user
     * @param city City of the user
     *
     * Constructor
     */
    public User(Integer id, String username, String password, String name, String email, String address, Date birth,
                boolean premium, String zone, String city) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birth = birth;
        this.premium = premium;
        this.zone = zone;
        this.city = city;
    }

    public User(String name, String email, String address, Date birth, boolean premium, String zone, String city, String password) {

        super();
        this.name = name;
        this.email = email;
        this.address = address;
        this.birth = birth;
        this.premium = premium;
        this.zone = zone;
        this.city = city;
        this.password = password;
    }

    /**
     * @return user id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id user id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return user real name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name user real name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address user address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return user birth date
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth user birth date
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * @return is premium or not
     */
    public boolean getPremium() {
        return premium;
    }

    /**
     * @param premium is premium or not
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * @return user zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param zone user zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     * @return user city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city user city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param o
     * @return if all elements of this user are equal
     *         to the elements of the user given by parameter
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof User)) return false;

        User user = (User)o;
        return (this.id == user.getId() &&
                this.username.equals(user.getUsername()) &&
                this.password.equals(user.getPassword()) &&
                this.name.equals(user.getName()) &&
                this.email.equals(user.getEmail()) &&
                this.address.equals(user.getAddress()) &&
                (this.premium == user.getPremium()) &&
                this.birth.equals(user.getBirth()) &&
                this.zone.equals(user.getZone()) &&
                this.city.equals(user.getCity()));
    }
}
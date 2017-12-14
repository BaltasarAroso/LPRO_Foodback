package com.foodback.foodback.logic;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

/**
 * Created by Foodback on 14/12/2017.
 */

public class User {

    /**
     * user id
     */
    @SerializedName("id")
    private long id;

    /**
     *  username
     */
    @SerializedName("username")
    private String username;

    /**
     * user password
     */
    @SerializedName("password")
    private String password;

    /**
     *  user real name
     */
    @SerializedName("name")
    private String name;

    /**
     * user email
     */
    @SerializedName("email")
    private String email;

    /**
     * user address
     */
    @SerializedName("address")
    private String address;

    /**
     * user birthdate
     */
    @SerializedName("birth")
    private LocalDate birth;

    /**
     * is premium or not
     */
    @SerializedName("premium")
    private boolean premium;

    /**
     * @param id user id
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
    public User(long id, String username, String password, String name, String email, String address, LocalDate birth,
                boolean premium) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birth = birth;
        this.premium = premium;
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
    public void setId(long id) {
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
    public LocalDate getBirth() {
        return birth;
    }
    /**
     * @param birth user birth date
     */
    public void setBirth(LocalDate birth) {
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

    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof User)) return false;

        User user = (User)o;
        if(this.id == user.getId() &&
                this.username.equals(user.getUsername()) &&
                this.password.equals(user.getPassword()) &&
                this.name.equals(user.getName()) &&
                this.email.equals(user.getEmail()) &&
                this.address.equals(user.getAddress()) &&
                (this.premium == user.getPremium()) &&
                this.birth.equals(user.getBirth()) )
            return true;
        else return false;
    }
}

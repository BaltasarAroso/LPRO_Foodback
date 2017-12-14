package com.foodback.foodback.logic;

/**
 * Created by foodback on 14/12/2017.
 */

public class Client {
    /**
     * client username
     */
    private String username;
    /**
     * client role (USER or ESTABLISHMENT or ADMIN)
     */
    private String role;
    /**
     * user id (if user)
     */
    private int users_id;
    /**
     * establishment id (if establishment)
     */
    private int establishment_id;

    /**
     * @param username client username
     * @param role client role
     * @param users_id user id
     * @param establishment_id establishment id
     *
     * Constructor
     */
    public Client(String username, String role, int users_id, int establishment_id) {
        this.username = username;
        this.role = role;
        this.users_id = users_id;
        this.establishment_id = establishment_id;
    }

    /**
     * @return client username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username client username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return client role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role client role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return user id
     */
    public int getUsers_id() {
        return users_id;
    }

    /**
     * @param users_id user id
     */
    public void setUsers_id(int users_id) {
        this.users_id = users_id;
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

}

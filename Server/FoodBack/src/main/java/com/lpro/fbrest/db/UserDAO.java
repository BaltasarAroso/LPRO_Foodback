package com.lpro.fbrest.db;

import java.time.LocalDate;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.db.UserMapper;

/**
 * DAO for user persistent data access
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO {
	
	/**
	 * @param name Name of the user
	 * @param email Email of the user
	 * @param address Address of the user
	 * @param birth Birth date of the user
	 * @param premium If user is premium or nor
	 * @param zone Zone of the user
	 * @param city City of the user  
	 * @return id of inserted user
	 */
	@SqlUpdate("INSERT INTO users "
			+ "VALUES (DEFAULT, :name, :email, :address, :birth, :premium, :zone, :city)")
	@GetGeneratedKeys
	public long insertUser(@Bind("name") String name,
					@Bind("email") String email,
					@Bind("address") String address,
					@Bind("birth") LocalDate birth,
					@Bind("premium") boolean premium,
					@Bind("zone") String zone,
					@Bind("city") String city);
	
	/**
	 * @param id Id of user to be deleted
	 * 
	 * Deletes user entry
	 */
	@SqlUpdate("DELETE FROM users "
			+ "WHERE id = :user_id")
	public void deleteUser(@Bind("user_id") long id);
	
	/**
	 * @param username Username to be searched
	 * @return User if it exists
	 */
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium, zone, city "
			+ "FROM users JOIN credential ON users_id = users.id "
			+ "WHERE username = :username")
	public User getUserByUsername(@Bind("username") String username);
	
	/**
	 * @param id ID to be searched
	 * @return User if it exists
	 */
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium, zone, city "
			+ "FROM users JOIN credential ON users_id = users.id "
			+ "WHERE users.id = :id")
	public User getUserById(@Bind("id") long id);
	
	/**
	 * @return List with all users
	 */
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium, zone, city "
			+ "FROM users JOIN credential ON users_id = users.id")
	public List<User> getAllUsers();

	@SqlUpdate("UPDATE users "
			+ "SET premium = true "
			+ "WHERE id = :id")
	public void upgradePremium(@Bind("id") long users_id);
	
}
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

@RegisterMapper(UserMapper.class)
public interface UserDAO {
	
	@SqlUpdate("INSERT INTO users "
			+ "VALUES (DEFAULT, :name, :email, :address, :birth, :premium)")
	@GetGeneratedKeys
	public long insertUser(@Bind("name") String name,
					@Bind("email") String email,
					@Bind("address") String address,
					@Bind("birth") LocalDate birth,
					@Bind("premium") boolean premium);
	
	@SqlUpdate("DELETE FROM users "
			+ "WHERE id = :user_id")
	public void deleteUser(@Bind("user_id") long id);
	
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium "
			+ "FROM users JOIN credential ON users_id = users.id "
			+ "WHERE username = :username")
	public User getUserByUsername(@Bind("username") String username);
	
	@SqlQuery("SELECT users.id, username, name, email, address, birth, premium "
			+ "FROM users JOIN credential ON users_id = users.id")
	public List<User> getAllUsers();
	
}
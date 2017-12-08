package com.lpro.fbrest.db;

import java.time.LocalDate;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.db.UserMapper;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
	
	//usa-se @sqlupdate para inserts/updates etc
	@SqlUpdate("INSERT INTO users "
			+ "VALUES (:username, :password, :name, :email, :address, :premium, :birth)")
	void insertUser(@Bind("username") String username, 
					@Bind("password") String password,
					@Bind("name") String name,
					@Bind("email") String email,
					@Bind("address") String address,
					@Bind("birth") LocalDate birth,
					@Bind("premium") String premium);
	
	//usa-se @SqlQuery para ir buscar info mas é preciso um mapper se for para preencher um objeto
	@SqlQuery("SELECT * "
			+ "FROM users "
			+ "WHERE username = :username")
	User getUser(@Bind("username") String username);
	
	@SqlQuery("SELECT * "
			+ "FROM users")
	List<User> getAllUsers();
	
	
	@SqlQuery("SELECT * "
			+ "FROM users "
			+ "WHERE username = :username AND password = :password")
	User getUserByNameAndPassword(@Bind("username") String username, @Bind("password") String password);

}
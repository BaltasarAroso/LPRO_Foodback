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
	
	//usa-se @sqlupdate para inserts/updates etc
	@SqlUpdate("INSERT INTO users "
			+ "VALUES (DEFAULT, :name, :email, :address, :birth, :premium)")
	@GetGeneratedKeys
	public long insertUser(@Bind("name") String name,
					@Bind("email") String email,
					@Bind("address") String address,
					@Bind("birth") LocalDate birth,
					@Bind("premium") boolean premium);

	//usa-se @SqlQuery para ir buscar info mas é preciso um mapper se for para preencher um objeto
	@SqlQuery("SELECT username, name, email, address, birth, premium "
			+ "FROM users JOIN credential ON users_id = users.id "
			+ "WHERE username = :username")
	User getUserByUsername(@Bind("username") String username);
	
	@SqlQuery("SELECT username, name, email, address, birth, premium "
			+ "FROM users JOIN credential ON users_id = users.id")
	List<User> getAllUsers();
	
}
package com.lpro.fbrest.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.auth.Client;

@RegisterMapper(ClientMapper.class)
public interface ClientDAO {

	@SqlQuery("SELECT * "
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE username = :username AND password = :password")
	public Client getClientByNameAndPassword(@Bind("username") String username, @Bind("password") String password);
	
	@SqlQuery("SELECT *"
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE username = :username")
	public Client getClient(@Bind("username") String username);
	
	@SqlUpdate("INSERT INTO credential "
			+ "VALUES (DEFAULT, :username, :password, 1, :user_id)")
	public void insertUserClient(@Bind("username") String username,
							@Bind("password") String password,
							@Bind("user_id") long user_id);

	@SqlUpdate("INSERT INTO credential (id, username, password, role_id, establishment_id) "
			+ "VALUES (DEFAULT, :username, :password, 2, :establishment_id)")
	public void insertEstablishmentClient(@Bind("username") String username,
										@Bind("password") String password,
										@Bind("establishment_id") long establishment_id);

}

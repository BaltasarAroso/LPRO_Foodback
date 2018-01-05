package com.lpro.fbrest.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.auth.Client;

/**
 * @author Daniel
 * 
 * DAO to access Client persistent data
 */
@RegisterMapper(ClientMapper.class)
public interface ClientDAO {

	/**
	 * @param username Username to be searched
	 * @param password Password to be searched
	 * @return Client if there is one that matches arguments
	 */
	@SqlQuery("SELECT * "
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE username = :username AND password = :password")
	public Client getClientByNameAndPassword(@Bind("username") String username, @Bind("password") String password);
	
	/**
	 * @param username Username to be searched
	 * @return Client if there is one that matches arguments
	 */
	@SqlQuery("SELECT *"
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE username = :username")
	public Client getClient(@Bind("username") String username);
	
	/**
	 * @param username Username to be inserted
	 * @param password Password to be inserted
	 * @param user_id Id of the user entry
	 * 
	 * Creates a user client entry
	 */
	@SqlUpdate("INSERT INTO credential "
			+ "VALUES (DEFAULT, :username, :password, 1, :user_id)")
	public void insertUserClient(@Bind("username") String username,
							@Bind("password") String password,
							@Bind("user_id") long user_id);

	/**
	 * @param username to be inserted
	 * @param password Password to be inserted
	 * @param establishment_id Id of the establishment entry
	 * 
	 * Creates a establishment client entry
	 */
	@SqlUpdate("INSERT INTO credential (id, username, password, role_id, establishment_id) "
			+ "VALUES (DEFAULT, :username, :password, 2, :establishment_id)")
	public void insertEstablishmentClient(@Bind("username") String username,
										@Bind("password") String password,
										@Bind("establishment_id") long establishment_id);
	
	/**
	 * @param username New username to be inserted
	 * @param password New password to be inserted
	 */
	@SqlUpdate("UPDATE credential "
			+ "SET username = :username, password = :password "
			+ "WHERE username = :lastusername")
	public void updateClient(@Bind("username") String username, @Bind("password") String password, @Bind("lastusername") String lastusername);

}

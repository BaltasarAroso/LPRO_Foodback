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
	@SqlQuery("SELECT credential.* , role "
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE username = :username AND password = :password")
	public Client getClientByNameAndPassword(@Bind("username") String username, @Bind("password") String password);
	
	/**
	 * @param username Username to be searched
	 * @return Client if there is one that matches arguments
	 */
	@SqlQuery("SELECT credential.* , role "
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
	 * @param tmp_establishment_id Id of the establishment entry
	 * 
	 * Creates a establishment client entry
	 */
	@SqlUpdate("INSERT INTO credential (id, username, password, role_id, tmp_establishment_id) "
			+ "VALUES (DEFAULT, :username, :password, 2, :tmp_establishment_id)")
	public void insertEstablishmentClient(@Bind("username") String username,
										@Bind("password") String password,
										@Bind("tmp_establishment_id") long tmp_establishment_id);
	
	/**
	 * @param username New username to be inserted
	 * @param lastusername Old username 
	 */
	@SqlUpdate("UPDATE credential "
			+ "SET username = :username "
			+ "WHERE username = :lastusername")
	public void updateClientUsername(@Bind("username") String username, @Bind("lastusername") String lastusername);
	
	/**
	 * @param password New password to be inserted
	 * @param lastusername Old username
	 */
	@SqlUpdate("UPDATE credential "
			+ "SET password = :password "
			+ "WHERE username = :lastusername")
	public void updateClientPassword(@Bind("password") String password, @Bind("lastusername") String lastusername);
	
	/**
	 * @param new_establishment_id ID to be inserted
	 * @param tmp_establishment_id ID before insertion
	 */
	@SqlUpdate("UPDATE credential "
			+ "SET establishment_id = :new_establishment_id "
			+ "WHERE tmp_establishment_id = :tmp_establishment_id")
	public void updateClientEstablishmentId(@Bind("new_establishment_id") long new_establishment_id, 
											@Bind("tmp_establishment_id") long tmp_establishment_id);

	/**
	 * @param tmp_id ID of tmp establishment
	 * @param id ID of client
	 */
	@SqlUpdate("UPDATE credential "
			+ "SET tmp_establishment_id = :tmp_id "
			+ "WHERE id = :id")
	public void addTmpEstablishment(@Bind("tmp_id") long tmp_id, @Bind("id") long id);

	/**
	 * @param tmp_establishment_id ID of tmp establishment
	 * @return Client if found
	 */
	@SqlQuery("SELECT credential.* , role "
			+ "FROM credential JOIN role ON role_id = role.id "
			+ "WHERE tmp_establishment_id = :tmp_id")
	public Client getClientByTmpEstablishment(@Bind("tmp_id") long tmp_establishment_id);

}

package com.lpro.fbrest.db;

import java.time.LocalDate;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.db.EstablishmentMapper;

@RegisterMapper(EstablishmentMapper.class)
public interface EstablishmentDAO {
	
	//usa-se @sqlupdate para inserts/updates etc
	@SqlUpdate("INSERT INTO establishment "
			+ "VALUES (default, :name, :id_cat, :address, :zone, :city, :email, :contact, :username, :password, :open_date, :delivey, :price, :schedule1, :schedule2)")
	void insertEstablishemnt(@Bind("id") Integer id, 
					@Bind("name") String name,
					@Bind("id_cat") Integer id_cat,
					@Bind("address") String address,
					@Bind("zone") String zone,
					@Bind("city") String city,
					@Bind("email") String email,
					@Bind("contact") Integer contact,
					@Bind("username") String username,
					@Bind("password") String password,
					@Bind("open_date") LocalDate open_date,
					@Bind("delivey") Boolean delivey,
					@Bind("price") Integer price,
					@Bind("schedule1") Integer schedule1,
					@Bind("schedule2") Integer schedule2);
	
	//usa-se @SqlQuery para ir buscar info mas é preciso um mapper se for para preencher um objeto
	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE name = :name")
	Establishment getEstablishment(@Bind("name") String name);
	
	@SqlQuery("SELECT * "
			+ "FROM establishment")
	List<Establishment> getAllEstablishments();
	
	
	//Para atualizar os parametros de um restaurante de acesso à base de dados
	@SqlUpdate("UPDATE establishment "
			+"SET name = :name, id_cat = :id_cat, address = :address, zone = :zone, city = :city, email = :email, contact = :contact, username = :username, password = :password, open_date = :open_date, delivey = :delivey, price = :price, schedule1 = :schedule1, schedule2 = :schedule2 "
			+"WHERE username = :username")
	void updateEstablishment(
			@Bind("id") Integer id, 
			@Bind("name") String name,
			@Bind("id_cat") Integer id_cat,
			@Bind("address") String address,
			@Bind("zone") String zone,
			@Bind("city") String city,
			@Bind("email") String email,
			@Bind("contact") Integer contact,
			@Bind("username") String username,
			@Bind("password") String password,
			@Bind("open_date") LocalDate open_date,
			@Bind("delivey") Boolean delivey,
			@Bind("price") Integer price,
			@Bind("schedule1") Integer schedule1,
			@Bind("schedule2") Integer schedule2);
}

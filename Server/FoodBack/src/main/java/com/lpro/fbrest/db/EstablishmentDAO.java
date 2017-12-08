package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.db.EstablishmentMapper;

@RegisterMapper(EstablishmentMapper.class)
public interface EstablishmentDAO {
	
	//usa-se @sqlupdate para inserts/updates etc
	@SqlUpdate("INSERT INTO establishment "
			+ "VALUES (DEFAULT, :name, :category_id, :address, :zone, :city, :email, :contact, :delivery, :avg_price)")
	@GetGeneratedKeys
	public long insertEstablishemnt(@Bind("name") String name,
							@Bind("category_id") int category_id,
							@Bind("address") String address,
							@Bind("zone") String zone,
							@Bind("city") String city,
							@Bind("email") String email,
							@Bind("contact") String contact,
							@Bind("delivery") Boolean delivery,
							@Bind("avg_price") int avg_price);
	
	@SqlQuery("SELECT * "
			+ "FROM establishment")
	List<Establishment> getAllEstablishments();	

	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE name = :name")
	Establishment getEstablishment(@Bind("name") String name);
	
	//Para atualizar os parametros de um restaurante de acesso à base de dados
	@SqlUpdate("UPDATE establishment "
			+"SET name = :name, category_id = :category_id, address = :address, zone = :zone, city = :city, email = :email, contact = :contact, delivery = :delivery, avg_price = :avg_price "
			+"WHERE id = :id")
	void updateEstablishment(@Bind("id") int id,
							@Bind("name") String name,
							@Bind("category_id") int category_id,
							@Bind("address") String address,
							@Bind("zone") String zone,
							@Bind("city") String city,
							@Bind("email") String email,
							@Bind("contact") String contact,
							@Bind("delivery") boolean delivery,
							@Bind("avg_price") int avg_price);
}

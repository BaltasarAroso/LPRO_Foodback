package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Category;
import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.db.EstablishmentMapper;

@RegisterMapper(EstablishmentMapper.class)
public interface EstablishmentDAO {
	
	//usa-se @sqlupdate para inserts/updates etc
	@SqlUpdate("INSERT INTO establishment "
			+ "VALUES (DEFAULT, :name, :category_id, :address, :zone, :city, :email, :contact, :delivery, :avg_price, :schedule1, :schedule2)")
	@GetGeneratedKeys
	public long insertEstablishemnt(@Bind("name") String name,
									@Bind("category_id") long category_id,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") Boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	@SqlUpdate("DELETE FROM establishment "
			+ "WHERE id = :establishment_id")
	public void deleteEstablishment(@Bind("establishment_id") long establishment_id);
	
	@SqlQuery("SELECT * "
			+ "FROM establishment")
	public List<Establishment> getAllEstablishments();	

	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE name = :name")
	public Establishment getEstablishment(@Bind("name") String name);
	
	//Para atualizar os parametros de um restaurante de acesso à base de dados
	@SqlUpdate("UPDATE establishment "
			+"SET name = :name, category_id = :category_id, address = :address, zone = :zone, city = :city, email = :email, contact = :contact, delivery = :delivery, avg_price = :avg_price, schedule1 = :schedule1, schedule2 = :schedule2 "
			+"WHERE id = :id")
	public void updateEstablishment(@Bind("id") long id,
									@Bind("name") String name,
									@Bind("category_id") long category_id,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	@SqlQuery("SELECT * "
			+ "FROM category")
	@RegisterMapper(CategoryMapper.class)
	public List<Category> getAllCategories();
	
}

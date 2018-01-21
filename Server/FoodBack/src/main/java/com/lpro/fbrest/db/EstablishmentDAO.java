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

/**
 * DAO to access establishment data
 */
@RegisterMapper(EstablishmentMapper.class)
public interface EstablishmentDAO {
	
	/**
	 * @param name
	 * @param category
	 * @param address
	 * @param zone
	 * @param city
	 * @param email
	 * @param contact
	 * @param delivery
	 * @param avg_price
	 * @param schedule1
	 * @param schedule2
	 * @return
	 */
	@SqlUpdate("INSERT INTO establishment "
			+ "VALUES (DEFAULT, :name, :category, :address, :zone, :city, :email, :contact, :delivery, :avg_price, :schedule1, :schedule2)")
	@GetGeneratedKeys
	public long insertEstablishment(@Bind("name") String name,
									@Bind("category") String category,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") Boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	/**
	 * @param establishment_id
	 */
	@SqlUpdate("DELETE FROM establishment "
			+ "WHERE id = :establishment_id")
	public void deleteEstablishment(@Bind("establishment_id") long establishment_id);
	
	/**
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment")
	public List<Establishment> getAllEstablishments();	

	/**
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE id = :id")
	public Establishment getEstablishmentById(@Bind("id") long id);
	
	/**
	 * @param id
	 * @param name
	 * @param category
	 * @param address
	 * @param zone
	 * @param city
	 * @param email
	 * @param contact
	 * @param delivery
	 * @param avg_price
	 * @param schedule1
	 * @param schedule2
	 */
	@SqlUpdate("UPDATE establishment "
			+"SET name = :name, category = :category, address = :address, zone = :zone, city = :city, email = :email, contact = :contact, delivery = :delivery, avg_price = :avg_price, schedule1 = :schedule1, schedule2 = :schedule2 "
			+"WHERE id = :id")
	public void updateEstablishment(@Bind("id") long id,
									@Bind("name") String name,
									@Bind("category") String category,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	/**
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM category")
	@RegisterMapper(CategoryMapper.class)
	public List<Category> getAllCategories();
	
	/**
	 * @param name
	 * @param category
	 * @param address
	 * @param zone
	 * @param city
	 * @param email
	 * @param contact
	 * @param delivery
	 * @param avg_price
	 * @param schedule1
	 * @param schedule2
	 * @return
	 */
	@SqlUpdate("INSERT INTO establishment_tmp "
			+ "VALUES (DEFAULT, :name, :category, :address, :zone, :city, :email, :contact, :delivery, :avg_price, :schedule1, :schedule2)")
	@GetGeneratedKeys
	public long insertTmpEstablishment(@Bind("name") String name,
									@Bind("category") String category,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") Boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	/**
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment_tmp "
			+ "WHERE id = :id")
	public Establishment getTmpEstablishment(@Bind("id") long id);
	
	/**
	 * @param establishment_id
	 */
	@SqlUpdate("DELETE FROM establishment_tmp "
			+ "WHERE id = :establishment_id")
	public void deleteTmpEstablishment(@Bind("establishment_id") long establishment_id);
	
	/**
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment_tmp")
	public List<Establishment> getAllTmpEstablishments();

	/**
	 * @param id
	 * @param name
	 * @param category
	 * @param address
	 * @param zone
	 * @param city
	 * @param email
	 * @param contact
	 * @param delivery
	 * @param avg_price
	 * @param schedule1
	 * @param schedule2
	 */
	@SqlUpdate("UPDATE establishment_tmp "
			+"SET name = :name, category = :category, address = :address, zone = :zone, city = :city, email = :email, contact = :contact, delivery = :delivery, avg_price = :avg_price, schedule1 = :schedule1, schedule2 = :schedule2 "
			+"WHERE id = :id")
	public void updateTmpEstablishment(@Bind("id") long id,
									@Bind("name") String name,
									@Bind("category") String category,
									@Bind("address") String address,
									@Bind("zone") String zone,
									@Bind("city") String city,
									@Bind("email") String email,
									@Bind("contact") String contact,
									@Bind("delivery") boolean delivery,
									@Bind("avg_price") int avg_price,
									@Bind("schedule1") String schedule1,
									@Bind("schedule2") String schedule2);
	
	/**
	 * @param category_id
	 * @return
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment JOIN category USING(category)"
			+ "WHERE category.id = :category_id")
	public List<Establishment> getEstablishmentsByCategoryId(@Bind("category_id") long category_id);

	/**
	 * @return
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment JOIN category USING(category)"
			+ "WHERE category.id >= 4")
	public List<Establishment> getRestaurants();

	/**
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment "
			+ "JOIN credential ON establishment.id = establishment_id "
			+ "JOIN establishment_tmp ON establishment_tmp.id = tmp_establishment_id "
			+ "WHERE tmp_establishment_id = :tmp_id")
	public Establishment getEstablishmentOfTmpEstablishment(@Bind("tmp_id") long id);
	
}

package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import com.lpro.fbrest.api.Category;
import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.db.EstablishmentMapper;

/**
 * DAO to access establishment data
 */
@UseStringTemplate3StatementLocator
@RegisterMapper(EstablishmentMapper.class)
public interface EstablishmentDAO {
	
	/**
	 * @param name of the establishment
	 * @param category of the establishment
	 * @param address of the establishment
	 * @param zone of the establishment
	 * @param city of the establishment
	 * @param email of the establishment
	 * @param contact of the establishment
	 * @param delivery of the establishment
	 * @param avg_price of the establishment
	 * @param schedule1 of the establishment
	 * @param schedule2 of the establishment
	 * @return id of the establishment
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
	 * @param establishment_id id of the establishment to delete
	 */
	@SqlUpdate("DELETE FROM establishment "
			+ "WHERE id = :establishment_id")
	public void deleteEstablishment(@Bind("establishment_id") long establishment_id);
	
	/**
	 * @return List of all establishments
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment")
	public List<Establishment> getAllEstablishments();	

	/**
	 * @param id of a establishment
	 * @return Return all parameters of a establishment in Jackson
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE id = :id")
	public Establishment getEstablishmentById(@Bind("id") long id);
	
	/**
	 * @param id of a establishment to update
	 * @param name of the establishment
	 * @param category of the establishment
	 * @param address of the establishment
	 * @param zone of the establishment
	 * @param city of the establishment
	 * @param email of the establishment
	 * @param contact of the establishment
	 * @param delivery of the establishment
	 * @param avg_price of the establishment
	 * @param schedule1 of the establishment
	 * @param schedule2 of the establishment
	 * 
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
	 * @return List of all establishments in a Category
	 */
	@SqlQuery("SELECT * "
			+ "FROM category")
	@RegisterMapper(CategoryMapper.class)
	public List<Category> getAllCategories();
	
	/**
	 * @param name of the establishment
	 * @param category of the establishment
	 * @param address of the establishment
	 * @param zone of the establishment
	 * @param city of the establishment
	 * @param email of the establishment
	 * @param contact of the establishment
	 * @param delivery of the establishment
	 * @param avg_price of the establishment
	 * @param schedule1 of the establishment
	 * @param schedule2 of the establishment
	 * @return id of the establishment
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
	 * @param id of a establishment
	 * @return a tmp establishment
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment_tmp "
			+ "WHERE id = :id")
	public Establishment getTmpEstablishment(@Bind("id") long id);
	
	/**
	 * @param establishment_id to delete
	 */
	@SqlUpdate("DELETE FROM establishment_tmp "
			+ "WHERE id = :establishment_id")
	public void deleteTmpEstablishment(@Bind("establishment_id") long establishment_id);
	
	/**
	 * @return List of all tmp establishments
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment_tmp")
	public List<Establishment> getAllTmpEstablishments();

	/**
	 * @param id of a establishment
	 * @param name of the establishment
	 * @param category of the establishment
	 * @param address of the establishment
	 * @param zone of the establishment
	 * @param city of the establishment
	 * @param email of the establishment
	 * @param contact of the establishment
	 * @param delivery of the establishment
	 * @param avg_price of the establishment
	 * @param schedule1 of the establishment
	 * @param schedule2 of the establishment
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
	 * @param category_id to select establishments
	 * @param sort boolean element TRUE(order elements) / FALSE(Not order elements)
	 * @param order_by select the column to ordered
	 * @param order_dir ASC or DESC
	 * @return List of all establishments of a category
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment JOIN category USING(category) "
			+ "WHERE category.id = :category_id "
			+ "ORDER BY <if(sort)> <order_by> <order_dir> NULLS LAST, <endif>  establishment.id ")
	public List<Establishment> getEstablishmentsByCategoryId(
			@Bind("category_id") long category_id,
			@Define("sort") boolean sort,
			@Define("order_by") String order_by,
			@Define("order_dir") String order_dir);

	/**
	 * @param sort boolean element  TRUE(order elements) / FALSE(Not order elements)
	 * @param order_by select the column to ordered
	 * @param order_dir ASC or DESC
	 * @return List of restaurants
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment JOIN category USING(category) "
			+ "WHERE category.id >= 4 "
			+ "ORDER BY <if(sort)> <order_by> <order_dir> NULLS LAST, <endif>  establishment.id ")
	public List<Establishment> getRestaurants(
			@Define("sort") boolean sort,
			@Define("order_by") String order_by,
			@Define("order_dir") String order_dir);

	/**
	 * @param id of a tmp establishment
	 * @return tmp establishment of a id
	 */
	@SqlQuery("SELECT establishment.* "
			+ "FROM establishment "
			+ "JOIN credential ON establishment.id = establishment_id "
			+ "JOIN establishment_tmp ON establishment_tmp.id = tmp_establishment_id "
			+ "WHERE tmp_establishment_id = :tmp_id")
	public Establishment getEstablishmentOfTmpEstablishment(@Bind("tmp_id") long id);

	/**
	 * @param search_string String to search with ILIKE
	 * @return List of establishments with name similar to search term
	 */
	@SqlQuery("SELECT * "
			+ "FROM establishment "
			+ "WHERE name ILIKE :search_string")
	public List<Establishment> getEstablishmentsByName(@Bind("search_string") String search_string);
	
}

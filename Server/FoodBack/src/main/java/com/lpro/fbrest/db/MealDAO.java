package com.lpro.fbrest.db;

import java.math.BigDecimal;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Meal;

/**
 * Mapper for Meal Class
 */
@RegisterMapper(MealMapper.class)
public interface MealDAO {
	
	/**
	 * @param meal Meal name
	 * @param price Meal price
	 * @param establishment_id ID of establishment
	 * @return ID of inserted meal
	 */
	@SqlUpdate("INSERT INTO meal "
			+ "VALUES (DEFAULT, :meal, :price, :establishment_id)")
	@GetGeneratedKeys
	long insertMeal(@Bind("meal") String meal,
					@Bind("price") BigDecimal price,
					@Bind("establishment_id") long establishment_id);
	
	/**
	 * @param establishment_id ID of establishment
	 * @return List of meals from the specified establishment
	 */
	@SqlQuery("SELECT id, meal, price, establishment_id "
			+ "FROM meal "
			+ "WHERE establishment_id = :establishment_id")
	List<Meal> getAllMeals(@Bind("establishment_id") long establishment_id);

	/**
	 * @param establishment_id ID of establishment
	 * @param meal Meal name
	 * @return Meal with specified name
	 */
	@SqlQuery("SELECT id, meal, price, establishment_id "
			+ "FROM meal "
			+ "WHERE establishment_id = :establishment_id AND meal = :meal")
	Meal getMealbyName(@Bind("establishment_id") long establishment_id, @Bind("meal") String meal);

}

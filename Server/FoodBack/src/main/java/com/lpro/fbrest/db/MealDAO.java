package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Meal;

@RegisterMapper(MealMapper.class)
public interface MealDAO {
	
	@SqlUpdate("INSERT INTO meal "
			+ "VALUES (DEFAULT, :meal, :price, :establishment_id)")
	@GetGeneratedKeys
	long insertMeal(@Bind("meal") String meal,
					@Bind("price") int price,
					@Bind("establishment_id") long establishment_id);

	
	@SqlQuery("SELECT id, meal, price, establishment_id "
			+ "FROM meal "
			+ "WHERE establishment_id = :establishment_id")
	List<Meal> getAllMeals(@Bind("establishment_id") long establishment_id);

	@SqlQuery("SELECT id, meal, price, establishment_id "
			+ "FROM meal "
			+ "WHERE establishment_id = :establishment_id AND meal = :meal")
	Meal getMealbyName(@Bind("establishment_id") long establishment_id, @Bind("meal") String meal);

	

}

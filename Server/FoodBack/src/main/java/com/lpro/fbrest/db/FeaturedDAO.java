package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Featured;

@RegisterMapper(FeaturedMapper.class)
public interface FeaturedDAO {

	/**
	 * @param meal_id
	 * @param duration
	 * @return 
	 */
	@SqlUpdate("INSERT INTO featured "
			+ "VALUES (DEFAULT, :meal_id, DEFAULT, :duration)")
	@GetGeneratedKeys
	public long insertFeatured(@Bind("meal_id") long meal_id, @Bind("duration") int duration);
	
	/**
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM featured "
			+ "WHERE id = :id")
	public Featured getFeaturedById(@Bind("id") long id);
	
	/**
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM featured "
			+ "WHERE now() < (added_date + interval '1' day * duration)")
	public List<Featured> getAllActiveFeatured();
	
	/**
	 * @param meal_id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM featured "
			+ "WHERE meal_id = :meal_id")
	public List<Featured> getFeaturedByMealId(@Bind("meal_id") long meal_id);
	
	/**
	 * @param id
	 */
	@SqlUpdate("DELETE FROM featured "
			+ "WHERE id = :id")
	public void deleteFeatured(@Bind("id") long id);
	
}

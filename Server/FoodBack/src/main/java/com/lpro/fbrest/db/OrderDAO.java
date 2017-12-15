package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Order;
import com.lpro.fbrest.api.Orders_meal;


@RegisterMapper(OrderMapper.class)
public interface OrderDAO {
	
	@SqlUpdate("INSERT INTO orders "
			+ "VALUES (DEFAULT, :users_id, :state)")
	@GetGeneratedKeys
	public long insertOrder(@Bind("users_id")long users_id,@Bind("state") String state);

	
	@SqlUpdate("INSERT INTO orders_meal "
			+ "VALUES (DEFAULT, :meal_id, :orders_id, :quantity)")
	@GetGeneratedKeys
	public long insertMealOrder(@Bind("meal_id") long meal_id,@Bind("orders_id") long orders_id,@Bind("quantity") int quantity);


	
	@SqlQuery("SELECT * "
			+ "FROM orders "
			+ "WHERE id = :order_id")
	public Order getOrder(@Bind("order_id") long order_id);
	
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT * "
			+ "FROM orders_meal "
			+ "WHERE orders_id = :order_id")
	public List<Orders_meal> getOrderMeals(@Bind("order_id") long order_id);
	

	
	

}

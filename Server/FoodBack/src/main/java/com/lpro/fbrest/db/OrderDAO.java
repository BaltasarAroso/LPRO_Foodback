package com.lpro.fbrest.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Order;


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


	
	//Esta função ainda não está a funcionar
	@SqlQuery("SELECT orders.users_id, orders.state, orders_meal.meal_id, orders_meal.quantity  "
			+ "FROM orders JOIN orders_meal ON orders.id = orders_id  "
			+"WHERE orders_id = :id")
	public Order getOrder(@Bind("id") long id);
	

	
	

}

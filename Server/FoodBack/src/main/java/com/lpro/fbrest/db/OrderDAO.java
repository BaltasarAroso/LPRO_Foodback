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
	
	/**
	 * @param users_id by create order
	 * @param state of the order
	 * @return id f the order
	 */
	@SqlUpdate("INSERT INTO orders "
			+ "VALUES (DEFAULT, :users_id, :state)")
	@GetGeneratedKeys
	public long insertOrder(@Bind("users_id")long users_id,@Bind("state") String state);

	
	/**
	 * @param meal to insert in a order
	 * @param orders_id to insert meal
	 * @param quantity of a meal to insert
	 * @return id of order
	 */
	@SqlUpdate("INSERT INTO orders_meal "
			+ "VALUES (DEFAULT, :meal, :orders_id, :quantity, 'waiting')")
	@GetGeneratedKeys
	public long insertMealOrder(@Bind("meal") String meal,@Bind("orders_id") long orders_id,@Bind("quantity") int quantity);
	
	/**
	 * @param order_id to get its informations
	 * @return order element
	 */
	@SqlQuery("SELECT * "
			+ "FROM orders "
			+ "WHERE id = :order_id")
	public Order getOrder(@Bind("order_id") long order_id);
	
	/**
	 * @param order_id to get its meals
	 * @return list of meals by a order
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT * "
			+ "FROM orders_meal "
			+ "WHERE orders_id = :order_id")
	public List<Orders_meal> getOrderMeals(@Bind("order_id") long order_id);

	/**
	 * @param users_id to get its orders 
	 * @return list of orders made by a user
	 */
	@SqlQuery("SELECT * "
			+ "FROM orders "
			+ "WHERE users_id = :users_id")
	public List<Order> getOrdersMadeByUser(@Bind("users_id") long users_id);

	/**
	 * @param establishment_id to get orders unprepared
	 * @return list of all orders unprepared
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT orders_meal.* "
			+ "FROM orders_meal JOIN meal ON meal.meal = orders_meal.meal "
			+ "WHERE establishment_id = :establishment_id AND state = 'waiting'")
	public List<Orders_meal> getUnpreparedOrdersByEstablishmentId(@Bind("establishment_id") long establishment_id);

	/**
	 * @param orders_meal_id to get dish owner
	 * @return id of the establishment 
	 */
	@SqlQuery("SELECT establishment_id "
			+ "FROM orders_meal JOIN meal ON meal.meal = orders_meal.meal "
			+ "WHERE orders_meal.id = :orders_meal_id")
	public long getDishOwnerId(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id to update the state
	 */
	@SqlUpdate("UPDATE orders_meal "
			+ "SET state = 'ready' "
			+ "WHERE id = :orders_meal_id")
	public void orders_mealIsReady(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id to get meal in a order
	 * @return meal in a specific order
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT * "
			+ "FROM orders_meal "
			+ "WHERE orders_id = (SELECT orders_id FROM orders_meal WHERE id = :orders_meal_id)")
	public List<Orders_meal> getOrderMealsByOneOfTheOrdersId(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id to update the state
	 */
	@SqlUpdate("UPDATE orders "
			+ "SET state = 'Encomenda em trânsito' "
			+ "WHERE id = (SELECT orders_id FROM orders_meal WHERE id = :orders_meal_id)")
	public void orderIsReady(@Bind("orders_meal_id") long orders_meal_id);


}

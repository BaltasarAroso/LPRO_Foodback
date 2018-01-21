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
	 * @param users_id
	 * @param state
	 * @return
	 */
	@SqlUpdate("INSERT INTO orders "
			+ "VALUES (DEFAULT, :users_id, :state)")
	@GetGeneratedKeys
	public long insertOrder(@Bind("users_id")long users_id,@Bind("state") String state);

	
	/**
	 * @param meal_id
	 * @param orders_id
	 * @param quantity
	 * @return
	 */
	@SqlUpdate("INSERT INTO orders_meal "
			+ "VALUES (DEFAULT, :meal_id, :orders_id, :quantity, 'waiting')")
	@GetGeneratedKeys
	public long insertMealOrder(@Bind("meal_id") long meal_id,@Bind("orders_id") long orders_id,@Bind("quantity") int quantity);
	
	/**
	 * @param order_id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM orders "
			+ "WHERE id = :order_id")
	public Order getOrder(@Bind("order_id") long order_id);
	
	/**
	 * @param order_id
	 * @return
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT * "
			+ "FROM orders_meal "
			+ "WHERE orders_id = :order_id")
	public List<Orders_meal> getOrderMeals(@Bind("order_id") long order_id);

	/**
	 * @param users_id
	 * @return
	 */
	@SqlQuery("SELECT * "
			+ "FROM orders "
			+ "WHERE users_id = :users_id")
	public List<Order> getOrdersMadeByUser(@Bind("users_id") long users_id);

	/**
	 * @param establishment_id
	 * @return
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT orders_meal.* "
			+ "FROM orders_meal JOIN meal ON meal.id = meal_id "
			+ "WHERE establishment_id = :establishment_id AND state = 'waiting'")
	public List<Orders_meal> getUnpreparedOrdersByEstablishmentId(@Bind("establishment_id") long establishment_id);

	/**
	 * @param orders_meal_id
	 * @return
	 */
	@SqlQuery("SELECT establishment_id "
			+ "FROM orders_meal JOIN meal ON meal.id = meal_id "
			+ "WHERE orders_meal.id = :orders_meal_id")
	public long getDishOwnerId(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id
	 */
	@SqlUpdate("UPDATE orders_meal "
			+ "SET state = 'ready' "
			+ "WHERE id = :orders_meal_id")
	public void orders_mealIsReady(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id
	 * @return
	 */
	@RegisterMapper(Orders_mealMapper.class)
	@SqlQuery("SELECT * "
			+ "FROM orders_meal "
			+ "WHERE orders_id = (SELECT orders_id FROM orders_meal WHERE id = :orders_meal_id)")
	public List<Orders_meal> getOrderMealsByOneOfTheOrdersId(@Bind("orders_meal_id") long orders_meal_id);

	/**
	 * @param orders_meal_id
	 */
	@SqlUpdate("UPDATE orders "
			+ "SET state = 'Encomenda em trânsito' "
			+ "WHERE id = (SELECT orders_id FROM orders_meal WHERE id = :orders_meal_id)")
	public void orderIsReady(@Bind("orders_meal_id") long orders_meal_id);


}

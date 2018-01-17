package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;


import com.lpro.fbrest.api.Order;
import com.lpro.fbrest.api.Orders_meal;
import com.lpro.fbrest.db.OrderDAO;

/**
 * Service for order management
 */
public abstract class OrderService{
	
	private static final String SUCCESS = "Success...";

	@CreateSqlObject
	abstract OrderDAO orderdao();

	/**
	 * @param order Order to be stored
	 * @return String for if successful
	 */
	public String newOrder(Order order)  {
		long order_id;
		try {
			order_id = orderdao().insertOrder(order.getUser_id(), "Encomenda em espera");
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		try {
			int i;
			for(i=0; i< order.getMeals().size(); i++) {
				orderdao().insertMealOrder(order.getMeals().get(i).getMeal_id(),
						order_id,
						order.getMeals().get(i).getQuantity());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		return SUCCESS;
	}
	

	/**
	 * @param users_id ID of user which orders are going to be searched
	 * @return List of orders
	 */
	public List<Order> getOrdersMadeByUser(long users_id) {
		List<Order> orders = null;
		try {
			orders = orderdao().getOrdersMadeByUser(users_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		if(orders == null) throw new WebApplicationException(404);
		if(orders.isEmpty()) throw new WebApplicationException(404);
		try {
			for(int i = 0; i < orders.size() ; i++) {
				orders.get(i).setMeals(orderdao().getOrderMeals(orders.get(i).getId()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		return orders;
	} 
	
	/**
	 * @param order_id ID of order to be searched
	 * @return Order if found
	 */
	public Order getOrder(long order_id) {
		Order order = null;
		try {
			order = orderdao().getOrder(order_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		if(order == null) throw new WebApplicationException(404);
		try {	
			order.setMeals(orderdao().getOrderMeals(order_id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		if(order.getMeals().isEmpty()) throw new WebApplicationException(404);
		return order;
	}

	/**
	 * @param establishment_id ID of establishment 
	 * @return List of Meals that have to be prepared
	 */
	public List<Orders_meal> getUnpreparedOrders(long establishment_id) {
		List<Orders_meal> meals;
		try {
			meals = orderdao().getUnpreparedOrdersByEstablishmentId(establishment_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		if(meals == null) throw new WebApplicationException(404);
		if(meals.isEmpty()) throw new WebApplicationException(404);
		return meals;
	}

	/**
	 * @param establishment_id ID of establishment that made request
	 * @param orders_meal_id ID of orders_meal to change to "ready"
	 */
	public void orderIsReady(long establishment_id, long orders_meal_id) {
		long golden_id = 0;
		try {
			golden_id = orderdao().getDishOwnerId(orders_meal_id);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
		if(golden_id != establishment_id) throw new WebApplicationException(403);
		
		try {
			orderdao().orders_mealIsReady(orders_meal_id);	
			List<Orders_meal> order_meals = orderdao().getOrderMealsByOneOfTheOrdersId(orders_meal_id);
			boolean ready = true;
			for(int i = 0; i < order_meals.size() ; i++) {
				if(!order_meals.get(i).getState().equals("ready")) ready = false;
			}
			if(ready) {
				orderdao().orderIsReady(orders_meal_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  
		}
	}

}

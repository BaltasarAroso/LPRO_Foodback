package com.lpro.fbrest.service;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;


import com.lpro.fbrest.api.Order;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.OrderDAO;

public abstract class OrderService{
	
	private static final String SUCCESS = "Success...";
	
	@CreateSqlObject
	abstract OrderDAO orderdao();

	public String newOrder(Order order, Client client)  {
		long order_id;
		try {
			order_id = orderdao().insertOrder(client.getUsers_id(), order.getState());
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
}

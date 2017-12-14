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

	
	//adiciona uma nova encomenda a um utilizador se este ainda não tiver nenhuma
	public String newOrder(Order order, Client client)  {
		long order_id;
		try {
			order_id = orderdao().insertOrder(client.getUsers_id(), order.getState());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  //não tenho a certeza deste erro
		}
		try {
			int i;
			for(i=0; i< order.getId_meals().size(); i++) {
				orderdao().insertMealOrder(order.getId_meals().get(i).getMeal_id(),
						order_id,
						order.getId_meals().get(i).getQuantity());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  //não tenho a certeza deste erro
		}
		
		return SUCCESS;
	}

	
	public Order getOrder(long order_id) {
		
		try {
			Order order = orderdao().getOrder(order_id);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);  //não tenho a certeza deste erro
		}
		
	} 
	
	

}

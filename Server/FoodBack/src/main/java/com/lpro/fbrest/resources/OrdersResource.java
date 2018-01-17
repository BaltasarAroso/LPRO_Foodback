package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.lpro.fbrest.api.Order;
import com.lpro.fbrest.api.Orders_meal;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.OrderService;


import io.dropwizard.auth.Auth;

/**
 * Resource to access orders
 */
@Path("/orders")
public class OrdersResource {

	/**
	 * Service for order management
	 */
	private OrderService orderService;
	
	/**
	 * @param orderService Service for order management
	 */
	public OrdersResource(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * @param client CLient that authenticated
	 * @param order Order to be stored
	 * @return Response with OK http status
	 */
	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newOrder(@Auth Client client, Order order) {
		if(client.getUsers_id()!=order.getUser_id())
			throw new WebApplicationException(403);
		orderService.newOrder(order);
		return Response.ok().build();
	}
	
	/**
	 * @param client Client that authenticated
	 * @return List of orders made by the authenticated user
	 */
	@GET
	@RolesAllowed("ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersMadeByUser(@Auth Client client) {
		return orderService.getOrdersMadeByUser(client.getUsers_id());
	}
	

	/**
	 * @param client Client that authenticated
	 * @param order_id ID of order to be searched
	 * @return Order if found
	 */
	@GET
	@RolesAllowed({"USER", "ADMIN"})
	@Path("/{order_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrder(@Auth Client client, @PathParam("order_id") long order_id) {
		Order order = orderService.getOrder(order_id);
		if(client.getUsers_id() == order.getUser_id()) return order;
		else throw new WebApplicationException(403);
	}
	
	/**
	 * @param client Client that authenticated
	 * @return List of unprepared orders
	 */
	@GET
	@RolesAllowed("ESTABLISHMENT")
	@Path("/establishment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders_meal> getUnpreparedOrders(@Auth Client client) {
		return orderService.getUnpreparedOrders(client.getEstablishment_id());
	}
	
	/**
	 * @param client Client that authenticated
	 * @param orders_meal_id ID of order meal to be made ready
	 * @return Response with OK http status
	 */
	@PUT
	@RolesAllowed("ESTABLISHMENT")
	@Path("/establishment/{orders_meal_id}")
	public Response orderIsReady(@Auth Client client, @PathParam("orders_meal_id") long orders_meal_id) {
		orderService.orderIsReady(client.getEstablishment_id(), orders_meal_id);
		return Response.ok().build();
	}
}

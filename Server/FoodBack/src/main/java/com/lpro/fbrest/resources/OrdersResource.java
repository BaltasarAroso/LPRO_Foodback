package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.lpro.fbrest.api.Order;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.OrderService;


import io.dropwizard.auth.Auth;

/**
 * 
 * @author beatrizcruz
 *
 */
@Path("/orders")
public class OrdersResource {

	private OrderService orderService;
	
	public OrdersResource(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newOrder(@Auth Client client, Order order) {
		if(client.getUsers_id()!=order.getUser_id())
			throw new WebApplicationException(403);
		orderService.newOrder(order);
		return Response.ok().build();
	}
	
	@GET
	@RolesAllowed({"USER", "ADMIN"})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersMadeByUser(@Auth Client client) {
		return orderService.getOrdersMadeByUser(client.getUsers_id());
	}
	

	@GET
	@RolesAllowed({"USER", "ADMIN"})
	@Path("/{order_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrder(@Auth Client client, @PathParam("order_id") long order_id) {
		Order order = orderService.getOrder(order_id);
		if(client.getUsers_id() == order.getUser_id()) return order;
		else throw new WebApplicationException(403);
	}
	
}

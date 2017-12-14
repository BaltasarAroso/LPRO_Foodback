package com.lpro.fbrest.resources;

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
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newOrder(@Auth Client client, Order order) {
		if(client.getUsers_id()==order.getUser_id())
			throw new WebApplicationException(400);
		orderService.newOrder(order, client);
		return Response.ok().build();
	}
	

	@GET
	@Path("/{order_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getOrder(@PathParam("order_id") long order_id) {
		return orderService.getOrder(order_id);
	}
	
}

package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Promotion;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.PromotionService;

import io.dropwizard.auth.Auth;

/**
 * Resource for promotion management
 */
@Path("/promotions")
public class PromotionsResource {
	
	/**
	 * Service to access promotion storage
	 */
	private PromotionService promotionService;
	
	/**
	 * @param promotionService Service to access promotion storage
	 */
	public PromotionsResource(PromotionService promotionService) {
		super();
		this.promotionService = promotionService;
	}
	
	/**
	 * @param promotion Promotion to be stored
	 * @return Response with OK http status
	 */
	@POST
	@RolesAllowed("ADMIN")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertPromotion(Promotion promotion) {
		promotionService.insertPromotion(promotion);
		return Response.ok().build();
	}
	
	/**
	 * @param client Client that authenticated
	 * @return List of all active promotions
	 */
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<Promotion> getActivePromotions(@Auth Client client) {
		if(client.getUsers_id() > 0) {
			if(!promotionService.userIsPremium(client.getUsers_id())) throw new WebApplicationException(403);
		}
		return promotionService.getActivePromotions();
	}
	
	/**
	 * @param promotion_id ID of promotion to be deleted
	 * @return Response with OK http status
	 */
	@DELETE
	@RolesAllowed("ADMIN")
	@Path("/{promotion_id}")
	public Response deletePromotion(@PathParam("promotion_id") long promotion_id) {
		promotionService.deletePromotion(promotion_id);
		return Response.ok().build();
	}

}

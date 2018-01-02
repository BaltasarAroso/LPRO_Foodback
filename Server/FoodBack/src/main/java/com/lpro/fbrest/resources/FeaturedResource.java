package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Featured;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.FeaturedService;

import io.dropwizard.auth.Auth;

@Path("/featured")
public class FeaturedResource {

	/**
	 * Service for Featured Class
	 */
	private FeaturedService featuredService;

	public FeaturedResource(FeaturedService featuredService) {
		super();
		this.featuredService = featuredService;
	}
	
	/**
	 * @param client Client that authenticated
	 * @param featured Featured object to be inserted
	 * @return Response with status
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("ESTABLISHMENT")
	public Response newFeatured(@Auth Client client, Featured featured) {
		featuredService.insertFeatured(featured);
		return Response.ok().build();
	}
	
	/**
	 * @return List of all active featured
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Featured> getAllFeatured(){
		return featuredService.getAllActiveFeatured();
	}
}

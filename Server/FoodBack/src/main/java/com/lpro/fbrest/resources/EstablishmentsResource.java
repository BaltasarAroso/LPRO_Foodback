package com.lpro.fbrest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableSet;
import com.lpro.fbrest.api.Category;
import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.EstablishmentService;

import io.dropwizard.auth.Auth;

	/**
	 * Resource for establishment management
	 */
	@Path("/establishments")
	public class EstablishmentsResource {
		
		private static ImmutableSet<String> orderByChoices = ImmutableSet.of("rating", "avg_price");
		private static ImmutableSet<String> orderDirChoices = ImmutableSet.of("ASC", "DESC");

		
		/**
		 * Service for the class Establishment
		 */
		private EstablishmentService establishmentService;
		
		/**
		 * @param establishmentService Service for the class Establishment
		 */
		public EstablishmentsResource(EstablishmentService establishmentService) {
			this.establishmentService = establishmentService;
	
		}
		
		/**
		 * @param establishment new establishment to be inserted in the database
		 * @return Response object with http status 
		 */
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response newEstablishment(@NotNull @Valid Establishment establishment) {
			establishmentService.newEstablishment(establishment);
			return Response.ok().build();	
		}
		
		/**
		 * @return All establishments
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getAllEstablishments() {
			return establishmentService.getAllEstablishments();
		}
		
		/**
		 * @param id ID of the wanted establishment
		 * @return Establishment with the ID specified
		 */
		@GET
		@Path("/{id}")  
		@Produces(MediaType.APPLICATION_JSON)
		public Establishment getEstablishmentById(@PathParam("id") long id) {
			return establishmentService.getEstablishmentById(id);
		}
		
		/**
		 * @param category_id ID of category
		 * @param sort If sort is needed
		 * @param order_by Column to order by
		 * @param order_dir Order asc or desc
		 * @return List of establishments
		 */
		@GET
		@Path("/filtered")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getEstablishmentsFiltered(
				@QueryParam("category_id") long category_id,
				@QueryParam("sort") Boolean sort,
				@QueryParam("order_by") String order_by,
				@QueryParam("order_dir") String order_dir) {
			
			if(sort == null) sort = new Boolean(false);
			else {
				if(order_by == null) order_by = "rating";
				if(order_dir == null) order_dir = "DESC";
				if (!orderByChoices.contains(order_by)) order_by = "rating"; //sanitize <order_by>
				if (!orderDirChoices.contains(order_dir)) order_dir = "asc";  //sanitize <order_dir>
			}
			return establishmentService.getEstablishmentsFiltered(
					category_id,
					sort,
					order_by,
					order_dir);
		}
		
		/**
		 * @param client Client that authenticated
		 * @return Establishment the client owns
		 */
		@GET
		@Path("/mine")
		@RolesAllowed("ESTABLISHMENT")
		@Produces(MediaType.APPLICATION_JSON)
		public Establishment getMyEstablishment(@Auth Client client) {
			return establishmentService.getEstablishmentById(client.getEstablishment_id());
		}
		
		/**
		 * @param newestablishment The establishment the be edited
		 * @return Response object with http status
		 */
		@PUT
		@RolesAllowed("ADMIN")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response changeEstablishment(@NotNull Establishment newestablishment) {
			establishmentService.editEstablishment(newestablishment.getId(), newestablishment);
			return Response.ok().build();	
		}
		
		/**
		 * @return List of all categories
		 */
		@GET
		@Path("/categories")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Category> getAllCategories(){
			return establishmentService.getAllCategories();
		}
		
		/**
		 * @param tmp_establishment_id ID of tmp establishment to be verified
		 * @return Response with OK http status if successful
		 */
		@PUT
		@RolesAllowed("ADMIN")
		@Path("/verify/{tmp_establishment_id}")
		public Response verifyTmpEstablishment(@PathParam("tmp_establishment_id") long tmp_establishment_id) {
			establishmentService.verifyTmpEstablishment(tmp_establishment_id);
			return Response.ok().build();
		}
		
		/**
		 * @param client Client that authenticated
		 * @return All TMP establishments if client is admin, if it's an establishment returns his tmp establishment
		 */
		@GET
		@RolesAllowed({"ADMIN", "ESTABLISHMENT"})
		@Path("/tmp")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getAllTmpEstablishments(@Auth Client client) {
			if(client.getEstablishment_id() <= 0) return establishmentService.getAllTmpEstablishments();
			else {
				List<Establishment> establishment = new ArrayList<Establishment>();
				establishment.add(establishmentService.getTmpEstablishment(client.getTmp_establishment_id()));
				return establishment;
			}
		}
		
		/**
		 * @param client Client that authenticated
		 * @param establishment Establishment to be inserted
		 * @return Response with OK http status
		 */
		@PUT
		@RolesAllowed("ESTABLISHMENT")
		@Path("/tmp")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response editTmpEstablishment(@Auth Client client, Establishment establishment) {
			establishmentService.editTmpEstablishment(client, establishment);
			return Response.ok().build();
		}
		
		/**
		 * @return List of establishment differences
		 */
		@GET
		@RolesAllowed("ADMIN")
		@Path("/tmp/diff")
		@Produces(MediaType.APPLICATION_JSON)
		public List<List<Establishment>> getAllTmpEstablishmentDifferences() {
			return establishmentService.getAllTmpEstablishmentDifferences();
		}
		
		/**
		 * @param search_string String to search with ILIKE
		 * @return List of establishments
		 */
		@GET
		@Path("/ilike/{search_string}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getEstablishmentsByName(@PathParam("search_string") String search_string) {
			return establishmentService.getEstablishmentsByName(search_string);
		}
		
}

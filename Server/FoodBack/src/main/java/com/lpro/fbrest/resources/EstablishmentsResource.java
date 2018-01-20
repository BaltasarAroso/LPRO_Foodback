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
		 * @return Establishments of that category
		 */
		@GET
		@Path("/filtered")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getEstablishmentsFiltered(@QueryParam("category_id") long category_id) {
			if(category_id > 0 && category_id != 123456789) 
				return establishmentService.getEstablishmentsByCategoryId(category_id);
			else if(category_id == 123456789) {
				return establishmentService.getRestaurants();
			}
			else
				return null;
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
		public List<Establishment> getAllTmpEstablishmentDifferences() {
			return establishmentService.getAllTmpEstablishmentDifferences();
		}
		
}

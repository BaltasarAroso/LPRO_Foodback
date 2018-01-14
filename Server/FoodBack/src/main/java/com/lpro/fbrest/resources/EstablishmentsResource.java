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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Category;
import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.EstablishmentService;

import io.dropwizard.auth.Auth;



	/**
	 * @author Beatriz 
	 *
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
		 * @param name of the wanted establishment
		 * @return Establishment with the name specified
		 */
		@GET
		@Path("/{name}")  
		@Produces(MediaType.APPLICATION_JSON)
		public Establishment getEstablishment(@PathParam("name") String name) {
			return establishmentService.getEstablishmentByName(name);
		}
		
		/**
		 * @param client Client that owns the establishment to be edited
		 * @param newestablishment The establishment the be edited
		 * @return Response object with http status
		 */
		@PUT
		@RolesAllowed("ESTABLISHMENT")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response changeEstablishment(@Auth Client client, @NotNull Establishment newestablishment) {
			establishmentService.editEstablishment(client.getEstablishment_id(), newestablishment);
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
		 * @return All TMP establishments
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
		
}

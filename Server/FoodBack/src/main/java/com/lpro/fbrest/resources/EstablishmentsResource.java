package com.lpro.fbrest.resources;

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
		 * DAO for the class Establishment
		 */
		private EstablishmentService establishmentService;
		
		/**
		 * @param establishmentdao DAO for the class Establishment
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
		@Path("/{name}")     //MUDAR O PARAM NAME PARA UMA QUERY (por causa dos espaços)
		@Produces(MediaType.APPLICATION_JSON)
		public Establishment getEstablishment(@PathParam("name") String name) {
			return establishmentService.getEstablishmentByName(name);
		}
		
		/**
		 * @param establishment the be edited
		 * @return Response object with http status
		 */
		@PUT
		@RolesAllowed("ESTABLISHMENT")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response changeEstablishment(@Auth Client client, @NotNull Establishment newestablishment) {
			establishmentService.editEstablishment(client.getEstablishment_id(), newestablishment);
			return Response.ok().build();	
		}
		
		
		
		

}

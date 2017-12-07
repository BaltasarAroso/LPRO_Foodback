package com.lpro.fbrest.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

import com.lpro.fbrest.core.Establishment;
import com.lpro.fbrest.core.User;
import com.lpro.fbrest.db.EstablishmentDAO;

import io.dropwizard.auth.Auth;


	@Path("/establishment")
	public class EstablishmentsResource {
		
		private EstablishmentDAO establishmentdao;
		
		public EstablishmentsResource(EstablishmentDAO establishmentdao) {
			this.establishmentdao = establishmentdao;
	
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response newEstablishment(@NotNull @Valid Establishment establishment) {
			try {
				establishmentdao.insertEstablishemnt(establishment.getId(),
							establishment.getName(),
							establishment.getId_cat(),
							establishment.getAddress(),
							establishment.getZone(),
							establishment.getCity(),
							establishment.getEmail(),
							establishment.getContact(),
							establishment.getUsername(),
							establishment.getPassword(),
							establishment.getOpen_date(),
							establishment.getDelivey(),
							establishment.getPrice(),
							establishment.getSchedule1(),
							establishment.getSchedule2());
				}
								
			catch (Exception e) {
				e.printStackTrace(System.out);
					return Response.serverError().entity("Establishment already exists").build();
				}
		
			return Response.ok().build();	
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Establishment> getAllEstablishments() {
			return establishmentdao.getAllEstablishments();
		}
		
		@GET
		@Path("/{name}")     //MUDAR O PARAM NAME PARA UMA QUERY (por causa dos espaços)
		@Produces(MediaType.APPLICATION_JSON)
		public Establishment getEstablishment(@PathParam("name") String name) {
			Establishment establishment = establishmentdao.getEstablishment(name);
			if(establishment == null) {
				throw new WebApplicationException(404);
			}
			establishment.setPassword(null);
			return establishment;
		}
		
		@PUT
		@Path("/update/{name}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response changeEstablishment(@NotNull @Valid Establishment establishment) {
			try {
				establishmentdao.updateEstablishment(
							establishment.getId(),
							establishment.getName(),
							establishment.getId_cat(),
							establishment.getAddress(),
							establishment.getZone(),
							establishment.getCity(),
							establishment.getEmail(),
							establishment.getContact(),
							establishment.getUsername(),
							establishment.getPassword(),
							establishment.getOpen_date(),
							establishment.getDelivey(),
							establishment.getPrice(),
							establishment.getSchedule1(),
							establishment.getSchedule2()
						);
				}
								
			catch (Exception e) {
				e.printStackTrace(System.out);
					return Response.serverError().entity("Establishment already exists").build();
				}
		
			return Response.ok().build();	
		}
		
		
		
		

}

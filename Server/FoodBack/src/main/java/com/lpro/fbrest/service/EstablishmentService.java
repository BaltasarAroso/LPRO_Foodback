package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.ClientDAO;
import com.lpro.fbrest.db.EstablishmentDAO;

public abstract class EstablishmentService {

	@CreateSqlObject
	abstract ClientDAO clientdao();
	
	@CreateSqlObject
	abstract EstablishmentDAO establishmentdao();
	
	public void newEstablishment(Establishment establishment) {
		long establishment_id;
		Client prev = clientdao().getClient(establishment.getUsername());
		if(prev != null) throw new WebApplicationException(Response.serverError()
													.entity("Username already taken!")
													.build());
		try {
			establishment_id = establishmentdao().insertEstablishemnt(establishment.getName(), 
																	establishment.getCategory_id(), 
																	establishment.getAddress(), 
																	establishment.getZone(), 
																	establishment.getCity(), 
																	establishment.getEmail(), 
																	establishment.getContact(), 
																	establishment.getDelivery(), 
																	establishment.getAvg_price());	
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		try {
			clientdao().insertEstablishmentClient(establishment.getUsername(), establishment.getPassword(), establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	public List<Establishment> getAllEstablishments() {
		return establishmentdao().getAllEstablishments();
	}
	
	public Establishment getEstablishmentByName(String name) {
		Establishment establishment = establishmentdao().getEstablishment(name);
		if(establishment == null) {
			throw new WebApplicationException(404);
		}
		return establishment;
	}
	
	public void editEstablishment(int establishment_id ,Establishment establishment) {
		try {
			establishmentdao().updateEstablishment(
						establishment_id,
						establishment.getName(),
						establishment.getCategory_id(),
						establishment.getAddress(),
						establishment.getZone(),
						establishment.getCity(),
						establishment.getEmail(),
						establishment.getContact(),
						establishment.getDelivery(),
						establishment.getAvg_price()
					);
			}
							
		catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
}

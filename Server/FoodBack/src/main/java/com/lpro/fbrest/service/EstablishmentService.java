package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Category;
import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.ClientDAO;
import com.lpro.fbrest.db.EstablishmentDAO;

public abstract class EstablishmentService {

	@CreateSqlObject
	abstract ClientDAO clientdao();
	
	@CreateSqlObject
	abstract EstablishmentDAO establishmentdao();
	
	/**
	 * @param establishment Establishment to be stored
	 */
	public void newEstablishment(Establishment establishment) {
		long establishment_id;
		Client prev = clientdao().getClient(establishment.getUsername());
		if(prev != null) throw new WebApplicationException(Response.serverError()
				.entity("{ \"code\": 409, \"message\": \"Username already exists.\" }")
				.type(MediaType.APPLICATION_JSON)
				.build());
		try {
			establishment_id = establishmentdao().insertTmpEstablishment(establishment.getName(), 
																	establishment.getCategory_id(), 
																	establishment.getAddress(), 
																	establishment.getZone(), 
																	establishment.getCity(), 
																	establishment.getEmail(), 
																	establishment.getContact(), 
																	establishment.getDelivery(), 
																	establishment.getAvg_price(),
																	establishment.getSchedule1(),
																	establishment.getSchedule2());	
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		try {
			clientdao().insertEstablishmentClient(establishment.getUsername(), establishment.getPassword(), establishment_id);
		} catch(Exception e) {
			try {
				establishmentdao().deleteTmpEstablishment(establishment_id);
			} catch(Exception err) {
				err.printStackTrace();
				throw new WebApplicationException(500);
			}
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * @return List of all establishments
	 */
	public List<Establishment> getAllEstablishments() {
		return establishmentdao().getAllEstablishments();
	}
	
	/**
	 * @return List of all temporary establishments
	 */
	public List<Establishment> getAllTmpEstablishments() {
		return establishmentdao().getAllTmpEstablishments();
	}
	
	/**
	 * @param name Name of establishment to be searched
	 * @return Establishment if it exists
	 */
	public Establishment getEstablishmentByName(String name) {
		Establishment establishment = establishmentdao().getEstablishment(name);
		if(establishment == null) {
			throw new WebApplicationException(404);
		}
		return establishment;
	}
	
	/**
	 * @param establishment_id ID of establishment to be edited
	 * @param establishment New establishment object
	 */
	public void editEstablishment(long establishment_id ,Establishment establishment) {
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
						establishment.getAvg_price(),
						establishment.getSchedule1(),
						establishment.getSchedule2()
					);
			}
							
		catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @return List of all categories
	 */
	public List<Category> getAllCategories(){
		try {
			return establishmentdao().getAllCategories();
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * @param tmp_establishment_id ID of the temporary establishment to be verified and "upgraded" to a normal establishment
	 */
	public void verifyTmpEstablishment(long tmp_establishment_id) {
		long establishment_id;
		Establishment establishment = null;
		try {
			establishment = establishmentdao().getTmpEstablishment(tmp_establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(establishment == null) throw new WebApplicationException(404);
		try {
			establishment_id = establishmentdao().insertEstablishment(establishment.getName(), 
					establishment.getCategory_id(), 
					establishment.getAddress(), 
					establishment.getZone(), 
					establishment.getCity(), 
					establishment.getEmail(), 
					establishment.getContact(), 
					establishment.getDelivery(), 
					establishment.getAvg_price(), 
					establishment.getSchedule1(), 
					establishment.getSchedule2());
			clientdao().updateClientEstablishmentId(establishment_id, tmp_establishment_id);
			establishmentdao().deleteTmpEstablishment(tmp_establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	public Establishment getTmpEstablishment(long tmp_establishment_id) {
		Establishment establishment = null;
		try {
			establishment = establishmentdao().getTmpEstablishment(tmp_establishment_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(establishment == null) throw new WebApplicationException(404);
		return establishment;
	}
	
}

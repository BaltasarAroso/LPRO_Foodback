package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Featured;
import com.lpro.fbrest.db.FeaturedDAO;

public abstract class FeaturedService {
	
	/**
	 * @return 
	 */
	@CreateSqlObject
	abstract FeaturedDAO featuredDao();
	
	public void insertFeatured(Featured featured) {
		try {
			featuredDao().insertFeatured(featured.getMeal_id(), 
					featured.getDuration());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	public List<Featured> getAllActiveFeatured(){
		return featuredDao().getAllActiveFeatured();
	}

}

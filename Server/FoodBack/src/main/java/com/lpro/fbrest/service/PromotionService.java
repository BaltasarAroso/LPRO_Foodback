package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Promotion;
import com.lpro.fbrest.db.PromotionDAO;

/**
 * Service for promotion management
 */
public abstract class PromotionService {

	@CreateSqlObject
	abstract PromotionDAO promotiondao();
	
	/**
	 * @param promotion Promotion to be stored
	 */
	public void insertPromotion(Promotion promotion) {
		try {
			promotiondao().insertPromotion(promotion.getTitle(), 
					promotion.getDescription(), 
					promotion.getCode(), 
					promotion.getGood_until());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @return List of active promotions
	 */
	public List<Promotion> getActivePromotions() {
		List<Promotion> promotions = null;
		try {
			promotions = promotiondao().getActivePromotions();
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(promotions == null) throw new WebApplicationException(404);
		if(promotions.isEmpty()) throw new WebApplicationException(404);
		return promotions;
	}
	
	/**
	 * @param promotion_id ID of promotion to be deleted
	 */
	public void deletePromotion(long promotion_id) {
		try {
			promotiondao().deletePromotion(promotion_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * @param users_id ID of user
	 * @return If user is premium or not
	 */
	public boolean userIsPremium(long users_id) {
		try {
			return promotiondao().userIsPremium(users_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
}

package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Meal;
import com.lpro.fbrest.db.MealDAO;

public abstract class MealService {

	@CreateSqlObject
	abstract MealDAO mealdao();
	
	public void newMeal(Meal meal) {
		try {
			mealdao().insertMeal(meal.getMeal(), meal.getPrice(), meal.getEstablishment_id());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		
	}
	
	//Lista de todos os pratos de um estabelecimento
	public List<Meal> getAllMeals(int establishment_id) {
		return mealdao().getAllMeals(establishment_id);
	}

	public Meal getMealbyName(int establishment_id, String meal) {
		Meal x = mealdao().getMealbyName(establishment_id, meal);
		if(x == null) {
			throw new WebApplicationException(404);
		}
		return x;
	}
	


}

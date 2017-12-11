package com.lpro.fbrest.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Meal;
import com.lpro.fbrest.service.MealService;

/**
 * @author beatrizcruz
 *
 */

@Path("/meals")
public class MealsResource {
	
	private MealService mealService;

	public MealsResource(MealService mealService) {
		this.mealService = mealService;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newMeal(@NotNull @Valid Meal meal) {
		mealService.newMeal(meal);
		return Response.ok().build();
	}
	
	
	

}

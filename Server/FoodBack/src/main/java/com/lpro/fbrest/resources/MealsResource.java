package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Meal;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.MealService;

import io.dropwizard.auth.Auth;

/**
 * @author beatrizcruz
 *
 */
@Path("/meals")
public class MealsResource {
	
	/**
	 * Service for meal management
	 */
	private MealService mealService;

	/**
	 * @param mealService Service for meal management
	 */
	public MealsResource(MealService mealService) {
		this.mealService = mealService;
	}
	
	/**
	 * @param client Client that authenticated
	 * @param meal Meal to be stored
	 * @return Response with ok http status
	 */
	@POST
	@RolesAllowed("ESTABLISHMENT")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newMeal(@Auth Client client ,@NotNull @Valid Meal meal) {
		if(client.getEstablishment_id() != meal.getEstablishment_id()) throw new WebApplicationException(403);
		mealService.newMeal(meal);
		return Response.ok().build();
	}
	
	/**
	 * @param establishment_id ID of establishment
	 * @return List of meals of the specified establishment
	 */
	@GET
	@Path("/{establishment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Meal> getAllMeals(@PathParam("establishment_id") int establishment_id){
		return mealService.getAllMeals(establishment_id);
	}
	
	/**
	 * @param establishment_id ID of establishment
	 * @param meal Name of meal
	 * @return Meal if found
	 */
	@GET
	@Path("/{establishment_id}/{meal}")
	@Produces(MediaType.APPLICATION_JSON)
	public Meal getMealbyName(@PathParam("establishment_id") long establishment_id, @PathParam("meal") String meal){
		return mealService.getMealbyName(establishment_id, meal);
	}

}

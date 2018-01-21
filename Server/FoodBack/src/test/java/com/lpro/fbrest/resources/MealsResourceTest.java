package com.lpro.fbrest.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.lpro.fbrest.api.Meal;
import com.lpro.fbrest.service.MealService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.*;

public class MealsResourceTest {
	
	private static final String SUCCESS = "Success...";
	
	private static final long TEST_IDMEAL = 2;
	private static final String TEST_MEAL = "bananas";
	private static final int TEST_PRICE = 2;
	private static final long TEST_ESTAID = 3;
	
	private static final String MEALS_ENDPOINT = "/meals";
	
	
	
	private static final MealService mealService = mock(MealService.class);
	
	@ClassRule
	public static final ResourceTestRule resources = 
		ResourceTestRule.builder().addResource(new MealsResource(mealService)).build();
	
	private final Meal meal = new Meal(TEST_IDMEAL,
									TEST_MEAL,
									TEST_PRICE,
									TEST_ESTAID);
	
	@Before
	public void setup() {
		when(mealService.newMeal(eq(meal))).thenReturn(SUCCESS);
		when(mealService.getMealbyName(3, "bananas")).thenReturn(meal);
		when(mealService.getMealbyName(1, "xxx")).thenThrow(new WebApplicationException(404));
	}
	
	@After
	public void tearDown() {
		reset(mealService);
	}
					
	@Test
	public void testNewMeal() {
		Response response = resources.target(MEALS_ENDPOINT).request()
				.post(Entity.entity(meal, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(response.getStatus(), Response.ok().build().getStatus());
	}
	
	@Test
	public void testGetMeal() {
		Meal mealResponse = resources.target(MEALS_ENDPOINT + "/3/bananas").request().get(Meal.class);
		assertEquals(mealResponse, meal);
		verify(mealService).getMealbyName(3, "bananas");
	}
	
	@Test
	public void testResponseFailUser() {
		Response response = resources.target(MEALS_ENDPOINT + "/1/xxx").request().get(Response.class);
		assertEquals(response.getStatus(), Response.status(Response.Status.NOT_FOUND).build().getStatus());
	}
	
	
	

}

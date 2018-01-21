package com.lpro.fbrest.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.lpro.fbrest.api.Establishment;
import com.lpro.fbrest.service.EstablishmentService;

import io.dropwizard.testing.junit.ResourceTestRule;

public class EstabliResourceTest {
	
	private static final String SUCCESS = "Success...";
	
	private static final long TEST_ID = 2;
	private static final String TEST_NAME = "DIPLOMATA";
	private static final String TEST_CATEGORY = "RESTAURANTE";
	private static final String TEST_ADDRESS = "JÁ ALI";
	private static final String TEST_ZONE = "BAIXA";
	private static final String TEST_CITY = "PORTO";
	private static final String TEST_EMAIL = "ALO@OLA.COM";
	private static final String TEST_CONTACT = "123456789";
	private static final String TEST_USERNAME = "DIPLO";
	private static final String TEST_PASS = "DIPLODIPLO";
	private static final boolean TEST_DELIV = true;
	private static final int TEST_AVG = 5;
	private static final String TEST_SCH1 = "AAAA";
	private static final String TEST_SCH2 = "BBBB";
	private static final double TEST_RATING = 3.1234;
	
	private static final String ESTABLISHMENTS_ENDPOINT = "/establishments";
	
	private static final EstablishmentService establishmentService = mock(EstablishmentService.class);
	
	@ClassRule
	public static final ResourceTestRule resources = 
		ResourceTestRule.builder().addResource(new EstablishmentsResource(establishmentService)).build();
	
	private Establishment establishment = new Establishment(TEST_ID, 
														TEST_NAME, 
														TEST_CATEGORY, 
														TEST_ADDRESS, 
														TEST_ZONE, 
														TEST_CITY, 
														TEST_EMAIL,
														TEST_CONTACT,
														TEST_USERNAME, 
														TEST_PASS, 
														TEST_DELIV, 
														TEST_AVG, 
														TEST_SCH1,
														TEST_SCH2, 
														TEST_RATING);

	@Before
	public void setup() {
		when(establishmentService.newEstablishment(eq(establishment))).thenReturn(SUCCESS);
		when(establishmentService.getEstablishmentById(4)).thenThrow(new WebApplicationException(404));
	}
	
	@After
	public void tearDown() {
		reset(establishmentService);
	}

	@Test
	public void testNewEstablishemnt() {
		Response response = resources.target(ESTABLISHMENTS_ENDPOINT).request()
				.post(Entity.entity(establishment, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(response.getStatus(), Response.ok().build().getStatus());
	}
	
	@Test
	public void testResponseFailEstablishment() {
		Response response = resources.target(ESTABLISHMENTS_ENDPOINT + "/4").request().get(Response.class);
		assertEquals(response.getStatus(), Response.status(Response.Status.NOT_FOUND).build().getStatus());
	}
	

}

package com.lpro.fbrest.resources;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.service.UserService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.*;

public class UsersResourceTest {
	
	private static final String SUCCESS = "Success...";
	private static final long TEST_ID = 4;
	private static final String TEST_USERNAME = "dan";
	private static final String TEST_PASSWORD = "mypass";
	private static final String TEST_NAME = "Daniel";
	private static final String TEST_EMAIL = "email@mail.com";
	private static final String TEST_ADDRESS = "Minha morada";
	private static final boolean TEST_PREMIUM = true;
	private static final LocalDate TEST_BIRTH = LocalDate.parse("1996-03-06");
	
	private static final String USERS_ENDPOINT = "/users";
	
	private static final UserService userService = mock(UserService.class);
	
	@ClassRule
	public static final ResourceTestRule resources = 
		ResourceTestRule.builder().addResource(new UsersResource(userService)).build();
	
	private final User user = new User(TEST_ID, 
									TEST_USERNAME,
									TEST_PASSWORD,
									TEST_NAME,
									TEST_EMAIL,
									TEST_ADDRESS,
									TEST_BIRTH,
									TEST_PREMIUM);
	
	@Before
	public void setup() {
		when(userService.getUserByUsername(eq("dan"))).thenReturn(user);
		when(userService.newUser(eq(user))).thenReturn(SUCCESS);
	}
	
	@After
	public void tearDown() {
		reset(userService);
	}
	
	@Test
	public void testGetUserByUsername() {
		User userResponse = resources.target(USERS_ENDPOINT + "/dan").request().get(User.class);
		assertEquals(userResponse, user);
		verify(userService).getUserByUsername("dan");
	}
	
	@Test
	public void testNewUser() {
		Response response = resources.target(USERS_ENDPOINT).request()
				.post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(response.getStatus(), Response.ok().build().getStatus());
	}

}

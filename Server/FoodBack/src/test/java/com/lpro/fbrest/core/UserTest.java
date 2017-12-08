package com.lpro.fbrest.core;

import com.lpro.fbrest.core.User;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final User person = new User("dan",
        								"mypass",
        								"Daniel",
        								"email@mail.com",
        								"Minha morada",
        								LocalDate.parse("1996-03-06"),
        								"blabla");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/user.json"), User.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
        final User person = new User("dan",
				"mypass",
				"Daniel",
				"email@mail.com",
				"Minha morada",
				LocalDate.parse("1996-03-06"),
				"blabla");
          
        assertThat(MAPPER.readValue(fixture("fixtures/user.json"), User.class)).isEqualTo(person);
    }
    
}
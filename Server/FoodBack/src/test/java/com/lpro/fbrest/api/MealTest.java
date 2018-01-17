package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MealTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Meal meal = new Meal(20, "Bacalhau", 12345, 15);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/meal.json"), Meal.class));
      
        assertThat(MAPPER.writeValueAsString(meal)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
        final Meal meal = new Meal(20, "Bacalhau", 12345, 15);
          
        assertThat(MAPPER.readValue(fixture("fixtures/meal.json"), Meal.class)).isEqualTo(meal);
    }
    
}
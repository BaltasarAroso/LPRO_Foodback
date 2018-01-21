package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
    		List<Orders_meal> meals = new ArrayList<Orders_meal>();
    		meals.add(new Orders_meal(20, "Prato 1", 25, 10, "estado.."));
    		meals.add(new Orders_meal(5, "Prato 2", 10, 25, "estado..."));
        final Order order = new Order(20, meals, 1234, "Pronto");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/order.json"), Order.class));
      
        assertThat(MAPPER.writeValueAsString(order)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
		List<Orders_meal> meals = new ArrayList<Orders_meal>();
		meals.add(new Orders_meal(20, "Prato 1", 25, 10, "estado.."));
		meals.add(new Orders_meal(5, "Prato 2", 10, 25, "estado..."));
		final Order order = new Order(20, meals, 1234, "Pronto");
          
        assertThat(MAPPER.readValue(fixture("fixtures/order.json"), Order.class)).isEqualTo(order);
    }
    
}
package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Category category = new Category(20, "Categoria");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/category.json"), Category.class));
      
        assertThat(MAPPER.writeValueAsString(category)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    		final Category category = new Category(20, "Categoria");
          
        assertThat(MAPPER.readValue(fixture("fixtures/category.json"), Category.class)).isEqualTo(category);
    }
    
}
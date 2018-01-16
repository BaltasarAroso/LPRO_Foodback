package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeaturedTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Featured featured = new Featured(20, 49, new Timestamp(123456789), 158);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/featured.json"), Featured.class));
      
        assertThat(MAPPER.writeValueAsString(featured)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    		final Featured featured = new Featured(20, 49, new Timestamp(123456789), 158);
          
        assertThat(MAPPER.readValue(fixture("fixtures/featured.json"), Featured.class)).isEqualTo(featured);
    }
    
}
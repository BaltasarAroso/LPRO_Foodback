package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PromotionTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Promotion promotion = new Promotion(25, "Título", "Descrição", "123ABC", LocalDate.parse("2018-03-06"));

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/promotion.json"), Promotion.class));
      
        assertThat(MAPPER.writeValueAsString(promotion)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    	final Promotion promotion = new Promotion(25, "Título", "Descrição", "123ABC", LocalDate.parse("2018-03-06"));
          
        assertThat(MAPPER.readValue(fixture("fixtures/promotion.json"), Promotion.class)).isEqualTo(promotion);
    }
    
}
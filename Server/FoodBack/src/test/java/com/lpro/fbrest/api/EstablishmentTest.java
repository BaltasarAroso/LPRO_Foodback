package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpro.fbrest.api.Establishment;

public class EstablishmentTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Establishment restaurante = new Establishment(29,
						        								"Ristoranti Italiano",
						        								"Categoria",
						        								"Morada",
						        								"minha zona",
						        								"minha cidade",
						        								"email@mail.com",
						        								"999999999",
						        								"user",
						        								"pass",
						        								true,
						        								25,
						        								"Horário 1",
						        								"Horário 2",
						        								3.87);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/establishment.json"), Establishment.class));

        assertThat(MAPPER.writeValueAsString(restaurante)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
        final Establishment restaurante = new Establishment(29,
															"Ristoranti Italiano",
															"Categoria",
															"Morada",
															"minha zona",
															"minha cidade",
															"email@mail.com",
															"999999999",
															"user",
															"pass",
															true,
															25,
						        								"Horário 1",
						        								"Horário 2",
						        								3.87);
          
        assertThat(MAPPER.readValue(fixture("fixtures/establishment.json"), Establishment.class)).isEqualTo(restaurante);
    }
}
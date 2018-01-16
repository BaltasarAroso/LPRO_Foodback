package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnswerTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Answer answer = new Answer(20, 24, "Resposta", new Timestamp(9000000));

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/answer.json"), Answer.class));
      
        assertThat(MAPPER.writeValueAsString(answer)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    		final Answer answer = new Answer(20, 24, "Resposta", new Timestamp(9000000));
          
        assertThat(MAPPER.readValue(fixture("fixtures/answer.json"), Answer.class)).isEqualTo(answer);
    }
    
}
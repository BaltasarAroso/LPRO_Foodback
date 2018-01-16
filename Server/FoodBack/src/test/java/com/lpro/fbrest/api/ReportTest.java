package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Report report = new Report(20, "Tipo", "Denúncia", 123, 456, 789);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/report.json"), Report.class));
      
        assertThat(MAPPER.writeValueAsString(report)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
        final Report report = new Report(20, "Tipo", "Denúncia", 123, 456, 789);
          
        assertThat(MAPPER.readValue(fixture("fixtures/report.json"), Report.class)).isEqualTo(report);
    }
    
}
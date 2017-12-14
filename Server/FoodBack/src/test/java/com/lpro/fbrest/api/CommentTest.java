package com.lpro.fbrest.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommentTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Comment comment = new Comment(20,
        								24,
        								13,
        								new Timestamp(9000000),
        								3,
        								"Comentário");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/comment.json"), Comment.class));
      
        assertThat(MAPPER.writeValueAsString(comment)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    	final Comment comment = new Comment(20,
    				24,
				13,
				new Timestamp(9000000),
				3,
				"Comentário");
          
        assertThat(MAPPER.readValue(fixture("fixtures/comment.json"), Comment.class)).isEqualTo(comment);
    }
    
}
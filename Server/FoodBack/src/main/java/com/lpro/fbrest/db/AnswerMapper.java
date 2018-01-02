package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Answer;

/**
 * @author Daniel
 *
 */
public class AnswerMapper implements ResultSetMapper<Answer>{

	/* (non-Javadoc)
	 * @see org.skife.jdbi.v2.tweak.ResultSetMapper#map(int, java.sql.ResultSet, org.skife.jdbi.v2.StatementContext)
	 */
	@Override
	public Answer map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Answer(r.getLong("id"),
				r.getLong("comment_id"),
				r.getString("answer"),
				r.getTimestamp("time_posted"));
	}

}

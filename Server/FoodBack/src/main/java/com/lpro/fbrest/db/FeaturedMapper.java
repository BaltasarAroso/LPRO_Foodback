package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Featured;

public class FeaturedMapper implements ResultSetMapper<Featured> {

	@Override
	public Featured map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Featured(r.getLong("id"), 
				r.getLong("meal_id"), 
				r.getTimestamp("added_date"), 
				r.getInt("duration"));
	}

}

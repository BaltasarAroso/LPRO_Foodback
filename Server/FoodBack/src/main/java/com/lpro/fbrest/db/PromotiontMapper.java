package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Promotion;

public class PromotiontMapper implements ResultSetMapper<Promotion>{

	@Override
	public Promotion map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		LocalDate good_until = r.getDate("good_until") == null ? null :  r.getDate("good_until").toLocalDate();
		return new Promotion(r.getLong("id"),
				r.getString("title"),
				r.getString("description"),
				r.getString("code"),
				good_until);
	}

}

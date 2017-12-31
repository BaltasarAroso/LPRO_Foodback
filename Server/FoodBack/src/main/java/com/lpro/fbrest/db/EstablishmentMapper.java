package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Establishment;

public class EstablishmentMapper implements ResultSetMapper<Establishment>{

	@Override
	public Establishment map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Establishment(r.getInt("id"),
						r.getString("name"),
						r.getInt("category_id"),
						r.getString("address"),
						r.getString("zone"),
						r.getString("city"),
						r.getString("email"),
						r.getString("contact"),
						null,
						null,
						r.getBoolean("delivery"),
						r.getInt("avg_price"),
						r.getString("schedule1"),
						r.getString("schedule2"));
	}
}
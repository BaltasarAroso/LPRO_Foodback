package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.core.Establishment;

public class EstablishmentMapper implements ResultSetMapper<Establishment>{

	@Override
	public Establishment map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Establishment(r.getInt("id"),
						r.getString("name"),
						r.getInt("id_cat"),
						r.getString("address"),
						r.getString("zone"),
						r.getString("city"),
						r.getString("email"),
						r.getInt("contact"),
						r.getString("username"),
						r.getString("password"),
						r.getDate("open_date"),
						r.getBoolean("delivey"),
						r.getInt("price"),
						r.getInt("schedule1"),
						r.getInt("schedule1"));
	}
}
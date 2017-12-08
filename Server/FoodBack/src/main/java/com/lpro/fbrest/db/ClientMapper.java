package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.auth.Client;

public class ClientMapper implements ResultSetMapper<Client>{

	@Override
	public Client map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Client(r.getString("username"),
						r.getString("role"),
						r.getInt("users_id"),
						r.getInt("establishment_id"));
	}
}

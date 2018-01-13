package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.auth.Client;

/**
 * @author Daniel
 * Mapper for ClientDAO
 */
public class ClientMapper implements ResultSetMapper<Client>{

	/* (non-Javadoc)
	 * @see org.skife.jdbi.v2.tweak.ResultSetMapper#map(int, java.sql.ResultSet, org.skife.jdbi.v2.StatementContext)
	 */
	@Override
	public Client map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Client(r.getLong("id"),
						r.getString("username"),
						r.getString("role"),
						r.getInt("users_id"),
						r.getInt("establishment_id"));
	}
}

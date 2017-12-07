package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.core.User;

public class UserMapper implements ResultSetMapper<User>{

	@Override
	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new User(r.getString("username"),
						r.getString("password"),
						r.getString("name"),
						r.getString("email"),
						r.getString("address"),
						r.getDate("birth"),
						r.getString("premium"));
	}
}
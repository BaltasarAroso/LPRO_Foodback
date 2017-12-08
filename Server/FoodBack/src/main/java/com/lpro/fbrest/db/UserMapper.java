package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.core.User;

public class UserMapper implements ResultSetMapper<User>{

	@Override
	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		LocalDate birth = r.getDate("birth") == null ? null :  r.getDate("birth").toLocalDate();
		return new User(r.getString("username"),
						null,
						r.getString("name"),
						r.getString("email"),
						r.getString("address"),
						birth,
						r.getString("premium"));
	}
}

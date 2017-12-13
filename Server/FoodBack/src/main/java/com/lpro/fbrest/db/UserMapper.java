package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.User;

/**
 * @author Daniel
 *
 * Mapper for UserDAO
 */
public class UserMapper implements ResultSetMapper<User>{

	/* (non-Javadoc)
	 * @see org.skife.jdbi.v2.tweak.ResultSetMapper#map(int, java.sql.ResultSet, org.skife.jdbi.v2.StatementContext)
	 */
	@Override
	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		LocalDate birth = r.getDate("birth") == null ? null :  r.getDate("birth").toLocalDate();
		return new User(r.getLong("id"),
						r.getString("username"),
						null,
						r.getString("name"),
						r.getString("email"),
						r.getString("address"),
						birth,
						r.getBoolean("premium"));
	}
}

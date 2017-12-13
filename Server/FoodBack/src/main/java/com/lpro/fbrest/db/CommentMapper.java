package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Comment;

/**
 * @author Daniel
 *
 * Mapper for CommentDAO
 */
public class CommentMapper implements ResultSetMapper<Comment> {

	/* (non-Javadoc)
	 * @see org.skife.jdbi.v2.tweak.ResultSetMapper#map(int, java.sql.ResultSet, org.skife.jdbi.v2.StatementContext)
	 */
	@Override
	public Comment map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Comment(r.getLong("id"),
						r.getInt("establishment_id"),
						r.getInt("commenter_id"),
						r.getTimestamp("time_posted"),
						r.getInt("rating"),
						r.getString("comment"));
	}

}

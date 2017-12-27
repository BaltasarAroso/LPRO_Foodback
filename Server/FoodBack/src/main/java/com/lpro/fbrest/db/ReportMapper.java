package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Report;

/**
 * @author Daniel
 *
 */
public class ReportMapper implements ResultSetMapper<Report>{

	/* (non-Javadoc)
	 * @see org.skife.jdbi.v2.tweak.ResultSetMapper#map(int, java.sql.ResultSet, org.skife.jdbi.v2.StatementContext)
	 */
	@Override
	public Report map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Report(r.getLong("id"),
				r.getString("type"),
				r.getString("report"),
				r.getLong("comment_id"),
			    r.getLong("establishment_id"),
			    r.getLong("reporter_id"));
	}

}

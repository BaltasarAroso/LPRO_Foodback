package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Category;

public class CategoryMapper implements ResultSetMapper<Category>{

	@Override
	public Category map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Category(r.getLong("id"), r.getString("category"));
	}

}

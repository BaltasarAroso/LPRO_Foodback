package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Order;

public class OrderMapper implements ResultSetMapper<Order>{
	
	@Override
	public Order map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Order(r.getLong("id"), 
					null, 
					r.getLong("users_id"), 
					r.getString("state"));
	} 
}

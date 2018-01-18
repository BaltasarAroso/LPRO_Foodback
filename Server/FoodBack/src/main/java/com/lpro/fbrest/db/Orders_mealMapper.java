package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Orders_meal;

public class Orders_mealMapper implements ResultSetMapper<Orders_meal>{
	
	@Override
	public Orders_meal map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Orders_meal(
				r.getLong("id"),
				r.getLong("meal_id"),
				r.getLong("orders_id"),
				r.getInt("quantity"),
				r.getString("state"));
	} 
}
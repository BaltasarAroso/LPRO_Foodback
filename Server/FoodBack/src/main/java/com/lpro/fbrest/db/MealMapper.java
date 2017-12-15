package com.lpro.fbrest.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.lpro.fbrest.api.Meal;


public class MealMapper implements ResultSetMapper<Meal>{
	
	@Override
	public Meal map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		
		return new Meal(r.getLong("id"),
						r.getString("meal"),
						r.getInt("price"),
						r.getInt("establishment_id"));
	}
	

}

package com.lpro.fbrest;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class FoodBackConfiguration extends Configuration {
	
	/**
	 * Database configurations
	 */
	@Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
	
    /**
     * @return database
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    /**
     * @param factory for database
     */
    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }
}

package com.lpro.fbrest;

import org.skife.jdbi.v2.DBI;

import com.lpro.fbrest.auth.UserAuthenticator;
import com.lpro.fbrest.core.User;
import com.lpro.fbrest.db.EstablishmentDAO;
import com.lpro.fbrest.db.UserDAO;
import com.lpro.fbrest.resources.EstablishmentsResource;
import com.lpro.fbrest.resources.UsersResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FoodBackApplication extends Application<FoodBackConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FoodBackApplication().run(args);
    }

    @Override
    public String getName() {
        return "FoodBack";
    }
    
    

    @Override
    public void initialize(final Bootstrap<FoodBackConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FoodBackConfiguration configuration,
                    final Environment environment) {
    	
    		//Database configurations
    		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		final UserDAO userdao = jdbi.onDemand(UserDAO.class);
		final EstablishmentDAO establishmentdao = jdbi.onDemand(EstablishmentDAO.class);
		
		
		//Resource configurations
		environment.jersey().register(new UsersResource(userdao));
		environment.jersey().register(new EstablishmentsResource(establishmentdao));
		
		
		
		//Auth configurations - de momento a usar Basic Auth
		environment.jersey().register(new AuthDynamicFeature(
				new BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(new UserAuthenticator(userdao))
				.setRealm("SECURITY REALM")
				.buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}

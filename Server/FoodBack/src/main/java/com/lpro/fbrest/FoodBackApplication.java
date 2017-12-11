package com.lpro.fbrest;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.auth.ClientAuthenticator;
import com.lpro.fbrest.auth.ClientAuthorizer;
import com.lpro.fbrest.db.ClientDAO;
import com.lpro.fbrest.resources.CommentsResource;
import com.lpro.fbrest.resources.EstablishmentsResource;
import com.lpro.fbrest.resources.ImagesResource;
import com.lpro.fbrest.resources.MealsResource;
import com.lpro.fbrest.resources.UsersResource;
import com.lpro.fbrest.service.CommentService;
import com.lpro.fbrest.service.EstablishmentService;
import com.lpro.fbrest.service.MealService;
import com.lpro.fbrest.service.UserService;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FoodBackApplication extends Application<FoodBackConfiguration> {

    /**
     * @param args of the command lime
     * @throws Exception If something goes wrong
     */
    public static void main(final String[] args) throws Exception {
        new FoodBackApplication().run(args);
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#getName()
     */
    @Override
    public String getName() {
        return "FoodBack";
    }
    
    /* (non-Javadoc)
     * @see io.dropwizard.Application#initialize(io.dropwizard.setup.Bootstrap)
     */
    @Override
    public void initialize(final Bootstrap<FoodBackConfiguration> bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
     */
    @Override
    public void run(final FoodBackConfiguration configuration,
                    final Environment environment) {
    	
    		//Database configurations
    		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		final ClientDAO clientdao = jdbi.onDemand(ClientDAO.class);
		
		
		//Resource configurations
		environment.jersey().register(new UsersResource(jdbi.onDemand(UserService.class)));
		environment.jersey().register(new EstablishmentsResource(jdbi.onDemand(EstablishmentService.class)));
		environment.jersey().register(new CommentsResource(jdbi.onDemand(CommentService.class)));
		environment.jersey().register(new MealsResource(jdbi.onDemand(MealService.class)));
		
		environment.jersey().register(new ImagesResource());
		
		
		//Auth configurations - de momento a usar Basic Auth
		environment.jersey().register(new AuthDynamicFeature(
				new BasicCredentialAuthFilter.Builder<Client>()
				.setAuthenticator(new ClientAuthenticator(clientdao))
				.setAuthorizer(new ClientAuthorizer())
				.setRealm("SECURITY REALM")
				.buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Client.class));
		environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

}

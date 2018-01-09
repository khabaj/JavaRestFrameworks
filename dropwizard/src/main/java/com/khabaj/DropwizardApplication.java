package com.khabaj;

import com.khabaj.common.repository.InMemoryPersonRepository;
import com.khabaj.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<Configuration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final Configuration configuration,
                    final Environment environment) {
        environment.jersey().register(new PersonResource(InMemoryPersonRepository.getInstance()));
    }
}

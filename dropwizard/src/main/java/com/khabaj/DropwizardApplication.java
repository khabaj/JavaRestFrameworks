package com.khabaj;

import com.khabaj.common.repository.InMemoryPersonRepository;
import com.khabaj.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        long time = System.currentTimeMillis();
        new DropwizardApplication().run(args);
        System.out.println("Started in " + (System.currentTimeMillis() - time) + " ms");
    }

    @Override
    public String getName() {
        return "Dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new PersonResource(InMemoryPersonRepository.getInstance()));
    }
}

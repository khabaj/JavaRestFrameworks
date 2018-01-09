package com.khabaj.restlet;

import com.khabaj.restlet.rest.PersonResource;
import com.khabaj.restlet.rest.PersonsResource;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class Application {

    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8090);

        component.getDefaultHost().attach("/persons", PersonsResource.class);
        component.getDefaultHost().attach("/persons/{id}", PersonResource.class);
        component.start();
    }
}

package com.khabaj.restlet;

import com.khabaj.restlet.rest.PersonResource;
import com.khabaj.restlet.rest.PersonsResource;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.engine.connector.ConnectorHelper;

import java.util.List;
import java.util.logging.Level;

public class Application {

    public static void main(String[] args) throws Exception {

        long time = System.currentTimeMillis();

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8090);
        component.getLogger().setLevel(Level.WARNING);
        component.getDefaultHost().attach("/persons", PersonsResource.class);
        component.getDefaultHost().attach("/persons/{id}", PersonResource.class);
        component.start();

        System.out.println("Started in " + (System.currentTimeMillis() - time) + " ms");

        List<ConnectorHelper<Server>> servers = Engine.getInstance().getRegisteredServers();
        for (ConnectorHelper<Server> server : servers) {
            System.out.println(server);
        }
    }
}

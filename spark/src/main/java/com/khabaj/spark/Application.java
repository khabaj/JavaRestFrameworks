package com.khabaj.spark;

import com.khabaj.spark.rest.PersonResource;

import static spark.Spark.port;
public class Application {

    public static void main(String[] args) {

        long time = System.currentTimeMillis();

        port(8090);
        new PersonResource();

        System.out.println("Started in " + (System.currentTimeMillis() - time) + " ms");
    }
}

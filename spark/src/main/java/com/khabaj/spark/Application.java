package com.khabaj.spark;

import com.khabaj.spark.rest.PersonResource;
import static spark.Spark.*;
public class Application {

    public static void main(String[] args) {
        port(8090);
        new PersonResource();
    }
}

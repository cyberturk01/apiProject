package com.project1.utilities.utilitiesAPI;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project1.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Every Api Step definition class should extend this class
 */

public class APIHelper {

    private static Gson gson;

    static {
        RestAssured.baseURI = ConfigurationReader.get("uri");
    }

    protected static RequestSpecification givenConfig() {
        RestAssured.useRelaxedHTTPSValidation();
        return given().
                header("Accept-Language", "en").header("Content-Type", "application/json");
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }
}

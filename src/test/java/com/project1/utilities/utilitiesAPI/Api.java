package com.project1.utilities.utilitiesAPI;

import io.restassured.response.Response;

import java.util.List;

public class Api extends APIHelper {

    public static final String PATH = "items/";

    public static Response getListOfColours(String endpoint) {
        return givenConfig().when().get(endpoint);
    }
    public static Response postDetails(List<String> itemModels) {
        return givenConfig().
                body(gson().toJson(itemModels)).
                when().
                post(PATH);
    }
    public static Response updateDetails(List<String> itemModels) {
        return givenConfig().
                body(gson().toJson(itemModels)).
                when().
                put(PATH);
    }

    public static Response deleteItem(String uniqueId) {
        return givenConfig().
                when().delete(PATH + uniqueId);
    }
}

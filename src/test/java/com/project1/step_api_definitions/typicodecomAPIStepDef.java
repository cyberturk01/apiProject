package com.project1.step_api_definitions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class typicodecomAPIStepDef {

    /*
        given
        when
        then
        get
     */
    @Test
    public void test1(){
        Response userIdRe = given().contentType(ContentType.JSON)
                .when().param("userId", 2)
                .get("https://jsonplaceholder.typicode.com/posts");

        JsonPath userId = userIdRe.jsonPath();

        List<Object> list = userId.getList("title");
        System.out.println(list.get(0));
    }
}

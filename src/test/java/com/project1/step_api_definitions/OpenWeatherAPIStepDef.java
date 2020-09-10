package com.project1.step_api_definitions;

import com.project1.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class OpenWeatherAPIStepDef {
    Response response;
    String token="f2513bcfd52b59232beab0e28a622735";
    String baseUri= ConfigurationReader.get("openweatherApi");
    String country="Frankfurt";
    //Status code 200
    @Test
    public void test1(){
        response= given().accept(ContentType.JSON)
                   .when().queryParam("q", country)
                    .and().queryParam("appid", token)
                    .when().get(baseUri);
        assertEquals(response.statusCode(), 200);
        assertTrue(response.contentType().contains("application/json"));
        assertTrue(response.asString().contains("Frankfurt"));
    }
    //Status code 401
    @Test()
    public void test2(){
        response= given().accept(ContentType.JSON)
                .when() .queryParam("q", country)
                .and().and().queryParam("appid", token+"1")
                .when().get(baseUri);
        assertEquals(response.statusCode(), 401);
    }
    //Use parameters
    @Test()
    public void test3(){
        response= given().accept(ContentType.JSON)
                .when() .param("q", country)
                .and().param("appid", token)
                .when().get(baseUri);
        System.out.println("Status Code= "+response.statusCode());
        System.out.println("response.prettyPeek() = " + response.prettyPeek());
//        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> weather = jsonPath.getList("weather"); //get as a map object
        System.out.println(weather.get(0).get("main"));
        String name = response.jsonPath().getString("name"); // get as a string from jsonpath
        System.out.println(name);
        Assert.assertEquals(weather.get(0).get("main"), "Clouds");


    }
    //Asset our testcase in Rest assured lib
    @Test()
    @DisplayName("Test REstAssured")
    public void test4(){
            given().accept(ContentType.JSON)
                .when() .param("q", country)
                .and().param("appid", token)
                .when().get(baseUri)
                .then().assertThat().statusCode(200);
    }
    //with Id param
    @Test()
    public void test5(){
        given().accept(ContentType.JSON)
                .when() .param("id", 2172797)
                .and().param("appid", token)
                .when().get(baseUri)
                .then().assertThat().statusCode(200);
    }
    @Test()
    public void test6(){
        response= given().accept(ContentType.JSON)
                .when() .param("zip", "201010,in")
                .and().param("appid", token)
                .when().get(baseUri);

        System.out.println("response.statusCode() = " + response.statusCode());
//        System.out.println("response.prettyPeek() = " + response.prettyPeek());
        JsonPath jsonPath = response.jsonPath();
        long time = response.timeIn(TimeUnit.MILLISECONDS);
        System.out.println(time);

        Assert.assertTrue( time<1000);


        String string = jsonPath.getString("weather[0].main");
        System.out.println(string);
    }

}

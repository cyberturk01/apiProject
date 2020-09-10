package com.project1.step_api_definitions;

import com.project1.utilities.ConfigurationReader;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created in Project1 by Gokhan on Apr, 2020
 */
public class HarryPotterStepDef {
    Response response;
    String key = ConfigurationReader.get("potter.key"); //instance variable
    String token = ConfigurationReader.get("harryPotterApiKey");
    String baseURI = ConfigurationReader.get("potter.api.uri");

    @Given("Send a get request to {string} Request includes")
    public void send_a_get_request_to_Request_includes(String url) {
        response = given().accept(ContentType.JSON)
                .when().queryParam(key, token)
                .when().get(baseURI + url);
    
    }
    @When("Verify that status code be {int}, and content type should be {string}")
    public void verify_that_status_code_be_and_content_type_should_be(int code, String content) {
        System.out.println("code = " + code);
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals("Status code should be "+code,  code, response.statusCode());
        Assert.assertEquals("Content Type should be " + content, response.contentType(), content);
    }
    @When("Verify that status code should not be {int}, and content type should be {string}")
    public void verify_that_status_code_should_not_be_and_content_type_should_be(int code, String content) {
        Assert.assertNotEquals("Status code should be "+code,  code, response.statusCode());
    }
    @Then("Verify that response body contains one of the following houses")
    public void verify_that_response_body_contains_one_of_the_following(List<String> dataTable) {
        Assert.assertTrue("Body should have one element", dataTable.contains(response.body().asString()));
    }
    @When("Query param key with value {string}")
    public void Query_param_key_with_value(String token) {
        response = given().accept("application/json")
                .when().queryParam(key, token)
                .when().get(baseURI+"characters");
    }
    @Then("Verify status code {int}, content type {string}")
    public void verify_status_code_content_type(int code, String content) {
        System.out.println("code = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals("Status code should be "+code,  code, response.statusCode());
        Assert.assertEquals("Content Type should be " + content,  content, response.contentType());
    }
    @Then("Verify response status line include message {string}")
    public void verify_response_status_line_include_message_Unauthorized(String status) {
        Assert.assertTrue(response.statusLine().contains(status));
    }
    @Then("Verify that response body says {string}")
    public void verify_that_response_body_says(String body) {
        Assert.assertTrue(response.body().asString().contains(body));
    }
    @Given("Send a get request to {string} Request with {string} and Header Accepts value {string}")
    public void send_a_get_request_to_Request_with_and_Header_Accepts_value(String characters, String toke, String content) {
        if(toke.length()==0)
            toke=toke;
        else
            toke=token;
        response = given().accept(content)
                .when().queryParam(key, toke)
                .when().get(baseURI + characters);
    }

    @Then("Verify response contains {int} characters")
    public void verify_response_contains_characters(int num) {
        List<String> list=response.body().as(List.class);
        Assert.assertEquals(num,list.size());
        System.out.println(list.size());

        given()
                    .accept(ContentType.JSON)
                .when()
                    .queryParam(key, token)
                    .get(baseURI+"characters").prettyPeek()
                .then().assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .header("Connection", "keep-alive")
                    .header("Server","cloudflare")
                    .body("name",hasItem("Winky"));


    }

}

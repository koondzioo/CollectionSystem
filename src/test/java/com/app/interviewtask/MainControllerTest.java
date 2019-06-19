package com.app.interviewtask;


import com.app.interviewtask.model.Image;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class MainControllerTest {

    private final String CONTEXT_PATH = "/app";

    @Before
    public void beforeTest() throws Exception {
        RestAssured.baseURI = "http://localhost:8081";
    }


    @Test
    public void statusCodeCheck() throws Exception {
        Response response = given()
                .contentType("text/plain")
                .body("https://wp.pl/")
                .when()
                .post(CONTEXT_PATH + "/images")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .extract()
                .response();
    }

    @Test
    public void checkImagesListSize() throws Exception {
        Response response = given()
                .contentType("text/plain")
                .body("https://google.pl/")
                .when()
                .post(CONTEXT_PATH + "/images")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .extract()
                .response();
        Assert.assertEquals(3, Arrays.asList(response.getBody().as(Image[].class)).size());
    }

    @Test
    public void invalidDownloadImage() throws Exception {
        given().when().get(CONTEXT_PATH + "/images/100000").then().statusCode(404);
    }


    @Test
    public void invalidDownloadText() throws Exception {
        given().when().get(CONTEXT_PATH + "/text/100000").then().statusCode(404);
    }

}

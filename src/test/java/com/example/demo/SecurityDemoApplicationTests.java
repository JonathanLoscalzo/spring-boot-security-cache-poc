package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityDemoApplicationTests {
    
    @Before
    public void setup() {
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = this.port;
    }

    @LocalServerPort
    private int port;


    @Test
    public void whenSignUpLogin() {

	JSONObject jsonObj = null;

	try {
	    jsonObj = new JSONObject().put("username", "pepo").put("password", "password");
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	given().contentType("application/json").body(jsonObj.toString()).when().post("/api/public/sign-up").then()
		.statusCode(200).extract();

	String authorization = given().contentType("application/json").body(jsonObj.toString())
		.post("/api/authenticate").then().statusCode(200).extract().header("Authorization");

	given().contentType("application/json").header("Authorization", authorization).get("/api/private").then()
		.statusCode(200);
    }

}

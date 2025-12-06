package com.api.tests;

import com.api.utilities.APIConstants;
import com.api.utilities.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AuthTests {

    private static final String LOGIN_DATA_PATH = "src/test/resources/testdata/loginData.json";

    @DataProvider(name = "LoginCredentials")
    public Object[][] getTestData() throws IOException {
        return JsonUtils.getLoginData(LOGIN_DATA_PATH);
    }

    @Test(dataProvider = "LoginCredentials")
    public void verifyTokenGeneration(String username, String password, int expectedStatusCode) {

        RestAssured.baseURI = APIConstants.BASE_URL;

        String requestBody = String.format("{\"username\": \"%s\", \"password\": \"%s\"}",
                username, password);

        System.out.println("\n--- Testing Login for User: " + username + " (Expected Status: " + expectedStatusCode + ") ---");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(APIConstants.LOGIN_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code check failed for user: " + username);

        if (expectedStatusCode == 201) {

            String token = response.jsonPath().getString("token");

            Assert.assertNotNull(token, "Token should be present for successful login (Status 201).");
            Assert.assertTrue(token.length() > 0, "Token string should not be empty.");

            APIConstants.authToken = token;
            System.out.println("Generated Auth Token: " + APIConstants.authToken);

        } else if (expectedStatusCode >= 400) {
            System.out.println("Login failed as expected for user: " + username + " with status: " + response.getStatusCode());
        }
    }
}
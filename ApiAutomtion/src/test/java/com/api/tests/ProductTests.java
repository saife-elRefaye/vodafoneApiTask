package com.api.tests;

import com.api.utilities.APIConstants;
import com.api.utilities.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductTests {

    private static final String PRODUCT_DATA_PATH = "src/test/resources/testdata/productData.json";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = APIConstants.BASE_URL;
    }

    @Test(priority = 1)
    public void verifyProductCreationAndContent() throws IOException {

        Map<String, Object> productData = JsonUtils.getProductData(PRODUCT_DATA_PATH, "create");

        String expectedTitle = (String) productData.get("title");
        Double expectedPrice = (Double) productData.get("price");

        String requestBody = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(productData);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(APIConstants.PRODUCTS_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 and product created");

        String responseTitle = response.jsonPath().getString("title");
        Double responsePrice = response.jsonPath().getDouble("price");
        Integer responseId = response.jsonPath().getInt("id");

        Assert.assertEquals(responseTitle, expectedTitle, "Title should match the request data from JSON.");
        Assert.assertEquals(responsePrice, expectedPrice, "Price should match the request data from JSON.");

        APIConstants.createdProductId = responseId;
        APIConstants.createdProductPrice = responsePrice;
        APIConstants.createdProductitle = responseTitle;
        System.out.println("Generated product: " + APIConstants.createdProductId +" , " + APIConstants.createdProductitle + " , " + APIConstants.createdProductPrice);

    }

    @Test(priority = 2)
    public void verifyProductUpdateAndContent() throws IOException {
        String productIdToUpdate = "1";

        Map<String, Object> updateData = JsonUtils.getProductData(PRODUCT_DATA_PATH, "update");

        String expectedTitle = (String) updateData.get("title");
        Double expectedPrice = (Double) updateData.get("price");

       String requestBody = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(updateData);


        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", productIdToUpdate)
                .body(requestBody)
                .when()
                .put(APIConstants.UPDATE_PRODUCT_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        String responseTitle = response.jsonPath().getString("title");
        Double responsePrice = response.jsonPath().getDouble("price");

        Assert.assertEquals(responseTitle, expectedTitle, "Title should be updated using data from JSON.");
        Assert.assertEquals(responsePrice, expectedPrice, "Price should be updated using data from JSON.");

        APIConstants.TitleProduct = responseTitle;
        APIConstants.PriceProduct = responsePrice;
        System.out.println("Title: " + APIConstants.TitleProduct+ " : "+APIConstants.PriceProduct);
    }
}
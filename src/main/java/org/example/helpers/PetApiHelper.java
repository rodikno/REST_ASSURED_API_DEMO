package org.example.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetApiHelper {

    // Method to create a basic request specification with common headers and configurations
    public static RequestSpecification createRequest() {
        return RestAssured.given()
                .baseUri(ConfigProvider.getBaseUrl()) // Base URI
                .header("Content-Type", "application/json") // Common header
                .log().all(); // Log request details
    }

    // Method to send a POST request to add a pet
    public static Response addPet(Object pet) {
        return createRequest()
                .body(pet) // Send the DTO object; it will be serialized automatically
                .post("/pet") // Endpoint for adding a pet
                .then()
                .statusCode(200) // Check expected status code
                .extract()
                .response(); // Return the response object for further validation if needed
    }

    // Method to send a GET request to fetch a pet by ID
    public static Response getPetById(long petId) {
        return createRequest()
                .get("/pet/" + petId) // Endpoint with pet ID
                .then()
                .statusCode(200) // Check expected status code
                .extract()
                .response(); // Return the response object for further validation if needed
    }
}


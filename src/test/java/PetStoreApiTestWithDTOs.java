import io.restassured.response.Response;
import org.example.models.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreApiTestWithDTOs extends BaseTest {



    // Pet ID to use in tests
    private static final long PET_ID = 123456789; // Change this to a unique ID for testing


    @Test
    public void testAddPetToStore() {
        // Define the pet details as a DTO
        Pet pet = new Pet(PET_ID, "Buddy", "available");


        // Send the POST request to add the pet
        Response response = given()
                .spec(requestSpec) // Apply the predefined request specification
                .body(pet) // Add the pet JSON as the request body
                .when()
                .post("/pet") // Send POST request to /pet endpoint
                .then()
                .statusCode(200) // Assert the status code is 200 (OK)
                .extract()
                .response();

        // Assert that the pet's ID matches the expected ID
        long responsePetId = response.jsonPath().getLong("id");
        Assert.assertEquals(responsePetId, PET_ID, "Pet was not added correctly");
    }

    @Test(dependsOnMethods = "testAddPetToStore") // Ensure this test runs after adding the pet
    public void testCheckPetExists() {
        // Send the GET request to check if the pet exists
        Response response = given()
                .spec(requestSpec) // Apply the predefined request specification
                .when()
                .get("/pet/" + PET_ID) // Make a GET request to the specific endpoint
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Validate that the returned pet ID matches the expected ID
        long responsePetId = response.jsonPath().getLong("id");
        Assert.assertEquals(responsePetId, PET_ID, "Pet ID does not match, pet was not found");

        // Validate that the returned pet's name and status are as expected
        String petName = response.jsonPath().getString("name");
        String petStatus = response.jsonPath().getString("status");
        Assert.assertEquals(petName, "Buddy", "Pet name does not match");
        Assert.assertEquals(petStatus, "available", "Pet status does not match");
    }
}

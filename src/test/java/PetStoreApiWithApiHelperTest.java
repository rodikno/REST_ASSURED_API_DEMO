import io.restassured.response.Response;
import org.example.helpers.PetApiHelper;
import org.example.models.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetStoreApiWithApiHelperTest extends BaseTest {


    // Pet ID to use in tests
    private static final long PET_ID = 123456789; // Change this to a unique ID for testing


    @Test
    public void testAddPetToStore() {
        // Define the pet details as a DTO
        Pet pet = new Pet(PET_ID, "Buddy", "available");

        Response response = PetApiHelper.addPet(pet);

        // Assert that the pet's ID matches the expected ID
        long responsePetId = response.jsonPath().getLong("id");
        Assert.assertEquals(responsePetId, PET_ID, "Pet was not added correctly");
    }

    @Test(dependsOnMethods = "testAddPetToStore") // Ensure this test runs after adding the pet
    public void testCheckPetExists() {
        // Send the GET request to check if the pet exists
        Response response = PetApiHelper.getPetById(PET_ID);

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

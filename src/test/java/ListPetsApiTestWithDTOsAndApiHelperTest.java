import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListPetsApiTestWithDTOsAndApiHelperTest extends BaseTest {


    @DataProvider(name = "statuses")
    public Object[][] petStatuses() {
        return new Object[][]{
                {"available"},
                {"pending"},
                {"sold"}
        };
    }

    @Test(dataProvider = "statuses")
    public void testGetPetsByStatus(String status) {
        // Sending GET request to find pets by status
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200) // Asserting that the status code is 200
                .extract()
                .response();

        // Logging the response for debugging purposes
        System.out.println("Response: " + response.asString());

        // Assert that the response body contains pets with the correct status
        response.jsonPath().getList("status").forEach(
                petStatus -> Assert.assertEquals(petStatus, status, "Pet status does not match the requested status.")
        );
    }
}

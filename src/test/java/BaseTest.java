import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {
    // Base URI for the Petstore API
    protected static final String BASE_URI = "https://petstore.swagger.io/v2";
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void commonSetup() {
        // Set the base URI for RestAssured
        RestAssured.baseURI = BASE_URI;
        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL), // Logs all details of the request
                new ResponseLoggingFilter(LogDetail.ALL) // Logs all details of the response
        );

        // Create a Request Specification
        requestSpec = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json");
    }
}

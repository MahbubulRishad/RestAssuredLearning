package jsonServer.test.api.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTestApi {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:3000/";
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}

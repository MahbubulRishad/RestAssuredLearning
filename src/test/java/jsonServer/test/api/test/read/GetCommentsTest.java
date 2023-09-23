package jsonServer.test.api.test.read;

import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseTestApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCommentsTest extends BaseTestApi {

    @Test
    public void getCommentsApiShouldSuccess(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body();
    }
}

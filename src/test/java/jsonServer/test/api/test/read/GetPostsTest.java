package jsonServer.test.api.test.read;

import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseTestApi;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostsTest extends BaseTestApi {

    @Test
    public void getPostsApiShouldSuccess(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getPostDetailsIDShouldSuccess(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(1))
                .body("author", equalTo("typicode"));
    }


}

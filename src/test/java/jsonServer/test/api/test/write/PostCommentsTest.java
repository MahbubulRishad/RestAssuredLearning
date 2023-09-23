package jsonServer.test.api.test.write;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.response.ValidatableResponse;
import jsonServer.test.api.test.BaseTestApi;
import jsonServer.test.api.util.General;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class PostCommentsTest extends BaseTestApi {

    @Test
    public void createCommentsShouldSuccess() {
        String body = LoremIpsum.getInstance().getCity();
        int postId = General.getRandomNumber();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", body);
        jsonObject.put("postId", postId);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void UpdatePUTCommentsShouldSuccess() {
        String body = LoremIpsum.getInstance().getCity();
        int postId = General.getRandomNumber();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", body);
        jsonObject.put("postId", postId);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .log().body();

        int commentID = validatableResponse.extract().jsonPath().getInt("id");

        body = LoremIpsum.getInstance().getCity();
        postId = General.getRandomNumber();

        JSONObject jsonObjectNew = new JSONObject();
        jsonObjectNew.put("body", body);
        jsonObjectNew.put("postId", postId);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObjectNew)
                .log().uri()
                .when()
                .put("/comments" + "/" + commentID)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void UpdatePATCHCommentsShouldSuccess() {
        String body = LoremIpsum.getInstance().getCity();
        int postId = General.getRandomNumber();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", body);
        jsonObject.put("postId", postId);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .log().body();

        int commentID = validatableResponse.extract().jsonPath().getInt("id");

        postId = General.getRandomNumber();

        JSONObject jsonObjectNew = new JSONObject();
        jsonObjectNew.put("postId", postId);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObjectNew)
                .log().uri()
                .when()
                .put("/comments" + "/" + commentID)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void deleteCommentsShouldSuccess() {
        String body = LoremIpsum.getInstance().getCity();
        int postId = General.getRandomNumber();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", body);
        jsonObject.put("postId", postId);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .log().body();

        int commentID = validatableResponse.extract().jsonPath().getInt("id");

        JSONObject jsonObjectNew = new JSONObject();

        given()
                .header("Content-Type", "application/json")
                .body(jsonObjectNew)
                .log().uri()
                .when()
                .delete("/comments" + "/" + commentID)
                .then()
                .statusCode(200)
                .log().body();
    }
}

package jsonServer.test.api.test.write;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.response.ValidatableResponse;
import jsonServer.test.api.test.BaseTestApi;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostPostsTest extends BaseTestApi {

    @Test(priority = 0)
    public void createAPostShouldSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(2));
        jsonObject.put("author", LoremIpsum.getInstance().getName());

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test(priority = 1)
    public void createAPostShouldBeSuccess2() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author));
    }

    @Test(priority = 2)
    public void updatePutAPostShouldBeSuccess() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .put("/posts/3")
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author));
    }

    @Test(priority = 3)
    public void updatePatchAPostShouldBeSuccess() {
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .patch("/posts/3")
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(author));
    }

    //    Dynamic Start
    @Test(priority = 4)
    public void dynamicUpdatePutAPostShouldBeSuccess() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author));

        int postId = validatableResponse.extract().jsonPath().getInt("id");

        title = LoremIpsum.getInstance().getTitle(2);
        author = LoremIpsum.getInstance().getName();

        JSONObject jsonObjectUpdate = new JSONObject();
        jsonObjectUpdate.put("title", title);
        jsonObjectUpdate.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObjectUpdate)
                .log().uri()
                .when()
                .put("/posts" + "/" + postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author));
    }

    @Test(priority = 5)
    public void dynamicDeleteAPostShouldBeSuccess() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author));

        int postId = validatableResponse.extract().jsonPath().getInt("id");

        JSONObject jsonObjectUpdate = new JSONObject();

        given()
                .header("Content-Type", "application/json")
                .body(jsonObjectUpdate)
                .log().uri()
                .when()
                .delete("/posts" + "/" + postId)
                .then()
                .statusCode(200)
                .log().body();
    }
}

package TestCase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class API_REQ {

    @Test
    public void GET() {

        RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", equalTo(1));
    }

    @Test
    public void WorkingWithPOST(){
        POST p = new POST(
                "Test",
                "Testing for assessment",
                1
        );
        int id = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .contentType(ContentType.JSON)
                .body(p)
                .when()
                .post("/posts")

                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("id",equalTo(101))
                .extract().path("id");
        System.out.println("Id is:" +id);
    }

    @Test
    public void PUT(){
        POST p = new POST(
                "Test",
                "Updating the existing value",
                1
        );
        RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .contentType(ContentType.JSON)
                .pathParam("id",1)
                .body(p)
                .when()
                .put("/posts/{id}")

                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("body",equalTo("Updating the existing value"))
                .body(matchesJsonSchemaInClasspath("test.json"));
    }

    @Test
    public void delete(){
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .contentType(ContentType.JSON)
                .pathParam("id",1)
                .when()
                .delete("/posts/{id}")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}

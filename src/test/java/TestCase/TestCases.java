package TestCase;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestCases {
    @Test
    public void TestingAPI() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        given()
                .header("Content-Type", "application/json")

                .when()
                .get("/objects")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(5000L))
                .assertThat().header("connection", "keep-alive");
               //.assertThat().body("name", equalTo("Apple MacBook Pro 19 (Updated Name)"));
    }
}

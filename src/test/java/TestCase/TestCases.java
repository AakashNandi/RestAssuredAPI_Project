package TestCase;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class TestCases {
    @Test
    public void TestingAPIGET() {

        Map<String, List<Integer>> queryParams = new HashMap<>();
        queryParams.put("id", Arrays.asList(3,5,10));
        given()
                .baseUri("https://api.restful-api.dev")
                .header("Content-Type", "application/json")
                .queryParams(queryParams)
        .when()             //shift + tab to format
                .get("/objects")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(5000L))
                .assertThat().header("connection", "keep-alive")
                .assertThat().body("id", hasItems("3","5","10"));
    }

    @Test
    public void TestingAPIPOST() {

        //convert json data that is to be POST, to String using...gpt //or use """  raw json data """
        String data =
                "{\"name\":\"Apple MacBook Pro 16\",\"data\":{\"year\":2026,\"price\":1849.99,\"CPU model\":\"Intel Core i9\",\"Hard disk size\":\"1 TB\"}}";
        given()
                .baseUri("https://api.restful-api.dev")
                .header("Content-Type", "application/json")
                .body(data)
        .when()             //shift + tab to format    //POST
                .post("/objects")
        .then()    //Checking from the POST Response if price is same....
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data.price", equalTo(1849.99f));
    }

    @Test
        public void TestSchema(){
            RestAssured.baseURI = "https://api.restful-api.dev";
            given()
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/objects/ff8081819782e69e019bdab12066113c")
                    .then()
                    .log().all()
                    .body(matchesJsonSchemaInClasspath("Schema.json"));

        }

}

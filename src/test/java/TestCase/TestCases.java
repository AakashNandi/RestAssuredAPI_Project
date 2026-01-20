package TestCase;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestCases {
    @Test
    public void TestingAPI() {

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
}

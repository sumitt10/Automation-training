package restassuredqueryandpath;

/**
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
**/
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class TestQueryAndPath {

	@Test
	void testQueryAndPathParameters() {

		given().pathParam("mypath", "users").queryParam("page", 2).queryParam("id", 5)

				.when().get("https://reqres.in/api/{mypath}").then().statusCode(200).log().all();
	}

}

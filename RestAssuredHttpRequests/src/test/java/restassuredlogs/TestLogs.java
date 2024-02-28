package restassuredlogs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class TestLogs {

	@Test
	void testLogs() {
		given().when().get("https://reqres.in/api/users?page=2").then().log().headers();
		//.body();
		//.cookies();
		//.headers();
	}
}

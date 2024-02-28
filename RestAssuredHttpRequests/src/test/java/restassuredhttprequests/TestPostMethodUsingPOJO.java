package restassuredhttprequests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class TestPostMethodUsingPOJO {

	@Test
	void testPostusingPOJO() {
		POJOClass data = new POJOClass();
		data.setName("Pavan");
		data.setJob("trainer");

		given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").then().statusCode(201).body("name", equalTo("Pavan"))
				.body("job", equalTo("trainer")).log().all();
	}

}

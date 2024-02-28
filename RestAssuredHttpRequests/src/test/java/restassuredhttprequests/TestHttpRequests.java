package restassuredhttprequests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class TestHttpRequests {
	int id;

	@Test(priority = 1)
	void getUsers() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 2)
	void createUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "pavan");
		data.put("job", "trainer");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void UpdateUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "john");
		data.put("job", "teacher");

		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users" + id).then()
				.statusCode(404).log().all();

	}

	@Test(priority = 4)
	void deleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}

}

package datavalidations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.logging.Logger;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Class Containing Test Cases on JSON Response Body i.e.Validations
 */

public class TestJsonResponseBody {
	private static final Logger log = Logger.getLogger(TestJsonResponseBody.class.getName());

	@Test(priority = 1)
	void testJsonResponseBody1() {
		Response res = given().contentType("application/json").when().get("https://reqres.in/api/users");
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 2)
	void testJsonResponseBody2() {
		Response res = RestAssured.given().contentType("application/json").when().get("https://reqres.in/api/users");

		String actual_last_name = res.jsonPath().get("data[3].last_name").toString();
		String expected_last_name = "Holt";
		log.info("Actual last-name :" + actual_last_name);
		log.info("expected last-name :" + expected_last_name);
		Assert.assertEquals(actual_last_name, expected_last_name);

	}

	@Test(priority = 3)
	void testJsonResponseBody3() {
		given().contentType("application/json").when().get("https://reqres.in/api/users").then()
				.body("data[3].last_name", equalTo("Holt"));

	}

	@Test(priority = 4)
	void testJsonResponseBody4() {
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users");

		res.then().statusCode(200);

		// Convert the response to a String
		String responseBody = res.getBody().asString();

		// Create a JSONObject from the response string
		JSONObject jsonObject = new JSONObject(responseBody);

		for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
			String email = jsonObject.getJSONArray("data").getJSONObject(i).getString("email");
			System.out.println("Emails list: " + email);
		}
	}

	@Test(priority = 5)
	void testJsonResponseBody5() {
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users");

		res.then().statusCode(200);

		// Convert the response to a String
		String responseBody = res.getBody().asString();

		// Create a JSONObject from the response string
		JSONObject jsonObject = new JSONObject(responseBody);

		boolean status = false;

		for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
			String email = jsonObject.getJSONArray("data").getJSONObject(i).getString("email");
			if (email.equals("eve.holt@reqres.in")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}

	@Test(priority = 6)
	void testJsonResponseBody6() {
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get("https://reqres.in/api/users");

		res.then().statusCode(200);

		// Convert the response to a String
		String responseBody = res.getBody().asString();

		// Create a JSONObject from the response string
		JSONObject jsonObject = new JSONObject(responseBody);

		double total_id = 0;
		double expected_total_id = 21;
		for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
			String id = jsonObject.getJSONArray("data").getJSONObject(i).get("id").toString();
			total_id = total_id + Double.parseDouble(id);
		}

		log.info("Expected Sum of id numbers :" + expected_total_id);
		log.info("Actual Sum of total of id numbers :" + total_id);
		Assert.assertEquals(total_id, expected_total_id);
	}

}

package schemavalidations;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class TestJsonSchemaValidation {

	@Test
	void jsonSchemavalidation() {
		given().when().get("https://reqres.in/api/users").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonBodySchema.json"));
	}

}

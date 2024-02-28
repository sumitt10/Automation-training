package restassuredcookiesandheaders;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestHeaders {

	@Test(priority = 1)
	void testHeaders() {
		given().when().get("https://www.google.com/").then().header("Content-Type", "text/html; charset=ISO-8859-1")
				.and().header("Content-Encoding", "gzip");
	}

	@Test(priority = 2)
	void getHeaders() {
		Response res = given().when().get("https://www.google.com/");
		String headervalue = res.getHeader("Content-Type");
		System.out.println("The value of Header is " + headervalue);
	}
}

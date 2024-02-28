package restassuredcookiesandheaders;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestCookies {

	@Test
	void getCookieInfo() {
		Response res = given().when().get("https://www.google.com/");
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of Cookie is " + cookie_value);

		Map<String, String> cookies_values = res.getCookies();
		System.out.println(cookies_values.keySet());
	}
}

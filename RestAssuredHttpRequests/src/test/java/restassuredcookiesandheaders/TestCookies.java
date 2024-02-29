package restassuredcookiesandheaders;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestCookies {
	private static final Logger log = Logger.getLogger(TestCookies.class.getName());

	@Test
	void getCookieInfo() {
		Response res = given().when().get("https://www.google.com/");
		String cookie_value = res.getCookie("AEC");
		log.info("Value of Cookie is " + cookie_value);

		Map<String, String> cookies_values = res.getCookies();
		log.info("Set of Cookies names :" + cookies_values.keySet());
	}
}

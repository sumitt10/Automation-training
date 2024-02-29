package datavalidations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestXmlResponseBody {

	@Test
	void testXMLResponse1() {
		given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1").then()
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	}

	@Test
	void testXMLResponse2() {
		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		String name = res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name")
				.toString();
		Assert.assertEquals(name, "Developer");
	}

	@Test
	void testXMLResponse3() {
		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		XmlPath xmlobj = new XmlPath(res.asString());
		List<String> traveller_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

		boolean status = false;
		for (String travellername : traveller_names) {
			if (travellername.equals("Developer")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
}

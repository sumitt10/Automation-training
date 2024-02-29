package testauthentications;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class TestFakerDataGeneration {
	private static final Logger log = Logger.getLogger(TestFakerDataGeneration.class.getName());

	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();

		String fullname = faker.name().firstName();
		String firstname = faker.name().lastName();

		String phonenumber = faker.phoneNumber().cellPhone();
		String email = faker.internet().safeEmailAddress();

		log.info("Full Name :" + fullname);
		log.info("First Name :" + firstname);
		log.info("Phone Number :" + phonenumber);
		log.info("Email :" + email);

	}
}

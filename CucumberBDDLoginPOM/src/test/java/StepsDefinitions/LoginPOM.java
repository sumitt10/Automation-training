package StepsDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginPOM {

	WebDriver driver = null;

	@Given("browser is open")
	public void browser__is_open() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@And("user is on login page")
	public void user__is_on_login_page() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user enters username and password")
	public void user__enters_username_and_password() throws InterruptedException {
		Thread.sleep(1000);
		LoginPage login = new LoginPage(driver);
		login.enterUsername("Admin");
		login.enterPassword("admin123");

	}

	@And("user clicks on login")
	public void user__clicks_on_login() {
		LoginPage login = new LoginPage(driver);
		login.clickLogin();
	}

	@Then("user is navigated to the home page")
	public void user__is_navigated_to_the_home_page() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.homePageHeaderDisplayed();
		driver.quit();
	}
}

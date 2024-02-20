package weblinktester;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrokenLinks {

	public static void main(String[] args) {
		// Path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to the specified URL
		driver.get("https://testpages.eviltester.com/styled/index.html");

		// Finding all the anchor elements
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		log.info("Total links on the web page: " + allLinks.size());

		int brokenLinksCount = 0;

		for (WebElement link : allLinks) {
			String url = link.getAttribute("href");

			// Checking if the link is broken or not 
			if (url != null && !url.isEmpty() && isLinkBroken(url)) {
				log.info("Broken Link: " + url);
				brokenLinksCount++;
			}
		}

		log.info("Number of broken links: " + brokenLinksCount);

		driver.quit();
	}

	// Method to check whether a link is broken or not 
	private static boolean isLinkBroken(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			return connection.getResponseCode() != 200;
		} catch (Exception e) {
			log.error("Exception occurred: " + e.getMessage());
			return true;
		}
	}
}

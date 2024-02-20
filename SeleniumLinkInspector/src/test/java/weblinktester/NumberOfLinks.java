package weblinktester;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberOfLinks {

	public static void main(String[] args) {
		// Path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\browserDriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to the specified URL
		driver.get("https://testpages.eviltester.com/styled/index.html");

		String url = "";

		// Finding all the anchor elements by tagname
		List<WebElement> allURLs = driver.findElements(By.tagName("a"));
		log.info("Total links on the web page: " + allURLs.size());

		// Iterate through the list of anchor elements and log their href attributes
		Iterator<WebElement> iterator = allURLs.iterator();
		while (iterator.hasNext()) {
			url = iterator.next().getAttribute("href");
			log.info(url);
		}

		driver.quit();
	}
}

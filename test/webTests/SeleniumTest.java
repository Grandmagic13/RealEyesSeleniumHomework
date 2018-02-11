package webTests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		String browserType = System.getProperty("browsertype");
		if (browserType.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserType.equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Unknown browsertype!");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
	}
}
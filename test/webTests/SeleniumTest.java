package webTests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\BenceHome\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
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
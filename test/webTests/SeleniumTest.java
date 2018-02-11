package webTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {

	private static final String USERNAME = System.getProperty("username");
	private static final String AUTOMATE_KEY = System.getProperty("automate_key");
	private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	private static final HashMap<String, String> defaultValues = initDefaultValues();
	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", getValueForCapability("browser"));
		caps.setCapability("browser_version", getValueForCapability("browser_version"));
		caps.setCapability("os", getValueForCapability("os"));
		caps.setCapability("os_version", getValueForCapability("os_version"));
		caps.setCapability("resolution", getValueForCapability("resolution"));

		driver = new RemoteWebDriver(new URL(URL), caps);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@After
	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	private static String getValueForCapability(String capability) {
		return System.getProperty(capability) != null ? System.getProperty(capability) : defaultValues.get(capability);
	}

	private static HashMap<String, String> initDefaultValues() {
		HashMap<String, String> defaults = new HashMap<>();
		defaults.put("browser", "Chrome");
		defaults.put("browser_version", "64.0");
		defaults.put("os", "Windows");
		defaults.put("os_version", "8.1");
		defaults.put("resolution", "1280x1024");
		return defaults;
	}
}
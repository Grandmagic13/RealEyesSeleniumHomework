package webTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import webObjects.WebAutoPage;

public class HomeworkTests extends SeleniumTest {

	@Test
	public void dividingByZeroShouldPopAnAlert() {
		// Narrative: #1 Dividing by zero should pop an alert
		// Steps:
		// 1. Open https://webautomationhw.realeyesit.com/
		WebAutoPage webAutoPage = openRealEyesWebAutoMationTestPage();
		enterTestUserCredentials(webAutoPage);
		// 3. Press login button
		webAutoPage.login();
		// 4. Divide an arbitrary number with 0
		webAutoPage.divideSevenByZero();
		// 5. Check if the popup showed and displaying the message
		assertFalse(webAutoPage.getAlertText().equals(""));
		// 6. Close the popup
		webAutoPage.closeAlert();
	}

	@Test
	public void checkMultiplicationFunctionality() {
		// Narrative: #2 Check multiplication functionality
		// Steps:
		// 1. Open https://webautomationhw.realeyesit.com/
		WebAutoPage webAutoPage = openRealEyesWebAutoMationTestPage();
		// 2. Log in using the following credentials: testuser / testpassword
		enterTestUserCredentials(webAutoPage);
		// 3. Press login button
		webAutoPage.login();
		// 4. Multiply any two arbitrary numbers
		webAutoPage.multiplySevenByFive();
		// 5. Check if the result is showing up
		assertEquals("Calculation result doesn't equal 35!", "35", webAutoPage.getResult());
	}

	private void enterTestUserCredentials(WebAutoPage webAutoPage) {
		webAutoPage.enterUserName("testuser");
		webAutoPage.enterPassword("testpassword");
	}

	private WebAutoPage openRealEyesWebAutoMationTestPage() {
		driver.get("https://webautomationhw.realeyesit.com/");
		WebAutoPage webAutoPage = new WebAutoPage(driver);
		assertTrue(webAutoPage.isInitialized());
		return webAutoPage;
	}

	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(HomeworkTests.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}
}

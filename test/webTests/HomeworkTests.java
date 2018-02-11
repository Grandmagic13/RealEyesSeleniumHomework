package webTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import webObjects.WebAutoPage;

public class HomeworkTests extends SeleniumTest {

	@Test
	public void dividingByZeroShouldPopAnAlert() throws InterruptedException {
		// Narrative: #1 Dividing by zero should pop an alert
		// Steps:
		// 1. Open https://webautomationhw.realeyesit.com/
		driver.get("https://webautomationhw.realeyesit.com/");
		WebAutoPage webAutoPage = new WebAutoPage(driver);
		assertTrue(webAutoPage.isInitialized());
		// 2. Log in using the following credentials: testuser / testpassword
		webAutoPage.enterUserName("testuser");
		webAutoPage.enterPassword("testpassword");
		// 3. Press login button
		webAutoPage.login();
		// 4. Divide an arbitrary number with 0
		webAutoPage.divideSevenByZero();
		// 5. Check if the popup showed and displaying the message
		assertFalse(webAutoPage.getAlertText().equals(""));
		// 6. Close the popup
		webAutoPage.closeAlert();
	}

}

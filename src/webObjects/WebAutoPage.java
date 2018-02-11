package webObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WebAutoPage extends PageObject {

	@FindBy(css = "img[alt=login-page]")
	private WebElement downArrow;

	@FindBy(tagName = "button")
	private WebElement loginButton;

	@FindBy(css = "input[type=text]")
	private WebElement enterUserName;

	@FindBy(css = "input[type=password]")
	private WebElement enterPassword;

	public WebAutoPage(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return downArrow.isDisplayed();
	}

	public void enterUserName(String userName) {
		enterInputTo(userName, enterUserName);
	}

	public void enterPassword(String password) {
		enterInputTo(password, enterPassword);
	}

	public void enterInputTo(String inputText, WebElement inputField) {
		inputField.clear();
		inputField.sendKeys(inputText);
	}

	public void login() {
		if (loginButton.getText().equals("Login")) {
			loginButton.click();
		} else {
			throw new ElementNotSelectableException("login button can't be found!");
		}
	}

	public void divideSevenByZero() {
		doOperationWithTwoNumbers("7", "0", "/");
	}

	public void multiplySevenByFive() {
		doOperationWithTwoNumbers("7", "5", "*");
	}

	private void doOperationWithTwoNumbers(String num1, String num2, String operation) {
		WebElement firstNumber = getButtonByText(num1);
		WebElement secondNumber = getButtonByText(num2);
		WebElement equals = getButtonByText("=");
		Select operationSelector = new Select(driver.findElement(By.tagName("select")));

		firstNumber.click();
		operationSelector.selectByValue(operation);
		secondNumber.click();
		equals.click();
	}

	private WebElement getButtonByText(String text) {
		for (WebElement button : driver.findElements(By.tagName("button"))) {
			if (button.getText().equals(text)) {
				return button;
			}
		}
		String exceptionMessage = String.format("'%s' button can't be found!", text);
		throw new ElementNotSelectableException(exceptionMessage);
	}

	public String getAlertText() {
		return getAlertPopup().getText();
	}

	public void closeAlert() {
		getAlertPopup().accept();
	}

	public String getResult() {
		return driver.findElement(By.className("result-val")).getText();
	}

	private Alert getAlertPopup() {
		return driver.switchTo().alert();
	}

}
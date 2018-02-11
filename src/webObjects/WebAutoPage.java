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
		WebElement seven = getButtonByText("7");
		WebElement zero = getButtonByText("0");
		WebElement equals = getButtonByText("=");
		Select operationSelector = new Select(driver.findElement(By.tagName("select")));

		seven.click();
		operationSelector.selectByValue("/");
		zero.click();
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

	private Alert getAlertPopup() {
		return driver.switchTo().alert();
	}

}
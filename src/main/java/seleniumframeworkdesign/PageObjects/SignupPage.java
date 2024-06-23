package seleniumframeworkdesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
	WebDriver driver;

	public SignupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='text']")
	WebElement nameField;
	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement emailField;
	@FindBy(xpath = "//button[@data-qa='signup-button']")
	WebElement signup;

	public void signUp(String name, String email) {
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		signup.click();

	}

}

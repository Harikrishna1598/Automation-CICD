package seleniumframeworkdesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import seleniumframeworkdesign.AbstractComponents.AbstractMethods;

public class AccountInformationPage extends AbstractMethods {
	WebDriver driver;

	public AccountInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='radio-inline']")
	List<WebElement> radioButtons;

	public void selectTitle(String title) {

		WebElement expectedTitle = radioButtons.stream().filter(actual -> {
			try {
				return actual.findElement(By.xpath(".//label[@class='top']")).getText().contains(title);
			} catch (Exception e) {
				System.err.println("Error finding 'Mrs': " + e.getMessage());
				return false;
			}
		}).findFirst().orElse(null);

		if (expectedTitle == null) {
			System.err.println("Could not find a radio button");
		} else {

			expectedTitle.findElement(By.xpath(".//input[@type='radio']")).click();
		}
	}

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordField;

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	@FindBy(id = "days")
	WebElement daysDropDown;

	@FindBy(id = "months")
	WebElement monthsDropDown;

	@FindBy(id = "years")
	WebElement yearsDropDown;

	public void selectDateOfBirth(String day, String month, String year) {
		Actions action = new Actions(driver);

		Select selectDay = new Select(daysDropDown);
		action.moveToElement(daysDropDown).click().build().perform();
		waitUntilElementAppears(daysDropDown);
		selectDay.selectByValue(day);

		Select selectMonth = new Select(monthsDropDown);
		action.moveToElement(monthsDropDown).click().build().perform();
		waitUntilElementAppears(monthsDropDown);
		selectMonth.selectByValue(month);

		Select selectYear = new Select(yearsDropDown);
		action.moveToElement(yearsDropDown).click().build().perform();
		waitUntilElementAppears(yearsDropDown);
		selectYear.selectByValue(year);
	}

	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> checkBoxes;

	public void clickCheckBox() {
		for (int i = 0; i < checkBoxes.size(); i++) {
			checkBoxes.get(i).click();
		}
	}

	@FindBy(xpath = "//input[@data-qa='first_name']")
	WebElement firstNameField;

	@FindBy(xpath = "//input[@data-qa='last_name']")
	WebElement lastNameField;

	public void fillName(String firstName, String lastName) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
	}

	@FindBy(xpath = "//input[@data-qa='company']")
	WebElement companyNameFieldElement;

	public void fillCompanyName(String companyName) {
		companyNameFieldElement.sendKeys(companyName);
	}

	@FindBy(xpath = "//input[@data-qa='address']")
	WebElement addressLineOne;

	@FindBy(xpath = "//input[@data-qa='address2']")
	WebElement addressLineTwo;

	public void fillCompanyAddress(String addressOne, String addressTwo) {
		addressLineOne.sendKeys(addressOne);
		addressLineTwo.sendKeys(addressTwo);
	}

	@FindBy(id = "country")
	WebElement countryDropDown;

	public void selectCountry(String countryName) {
		countryDropDown.click();
		Select countrySelect = new Select(countryDropDown);
		countrySelect.selectByValue(countryName);
	}

	@FindBy(xpath = "//input[@data-qa='state']")
	WebElement stateField;

	@FindBy(xpath = "//input[@data-qa='city']")
	WebElement cityField;

	public void fillStateAndCity(String state, String city) {
		stateField.sendKeys(state);
		cityField.sendKeys(city);
	}

	@FindBy(xpath = "//input[@data-qa='zipcode']")
	WebElement zipcodeField;

	public void fillZipcode(String zipcode) {
		zipcodeField.sendKeys(zipcode);
	}

	@FindBy(xpath = "//input[@data-qa='mobile_number']")
	WebElement mobileNumberField;

	public void fillMobileNumber(String mobileNumber) {
		mobileNumberField.sendKeys(mobileNumber);
	}

	@FindBy(xpath = "//button[@data-qa='create-account']")
	WebElement createButtonElement;

	public void clickCreateAccount() {
		waitUntilElementAppears(createButtonElement);
		createButtonElement.click();
	}

}

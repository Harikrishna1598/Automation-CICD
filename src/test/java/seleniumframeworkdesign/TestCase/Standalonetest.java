package seleniumframeworkdesign.TestCase;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import seleniumframeworkdesign.PageObjects.AccountInformationPage;
import seleniumframeworkdesign.PageObjects.HomePage;
import seleniumframeworkdesign.PageObjects.SignupPage;
import seleniumframeworkdesign.TestComponents.BaseTest;

public class Standalonetest extends BaseTest {
	@Test(dataProvider = "getTestData")
	public void submitForm(HashMap<String, String> input) throws IOException, TimeoutException {

		HomePage homePage = launchApplication();

		SoftAssert softAssert = new SoftAssert();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		Boolean verifyHomePageBoolean = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[4]"))
				.isDisplayed();

		softAssert.assertTrue(verifyHomePageBoolean);

		homePage.clickSignUp();

		Boolean verifySignUpText = driver.findElement(By.xpath("//div[@class='signup-form']/h2")).isDisplayed();
		softAssert.assertTrue(verifySignUpText);

		SignupPage signUpPage = new SignupPage(driver);
		signUpPage.signUp(input.get("name"), input.get("emailid"));

		Boolean verifyAccountInformationText = driver.findElement(By.xpath("//div[@class='login-form']/h2/b"))
				.isDisplayed();
		softAssert.assertTrue(verifyAccountInformationText);

		AccountInformationPage accountInformationPage = new AccountInformationPage(driver);
		accountInformationPage.selectTitle(input.get("Title"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		accountInformationPage.enterPassword(input.get("password"));

		accountInformationPage.selectDateOfBirth(input.get("Date"), input.get("Month"), input.get("Year"));

		accountInformationPage.clickCheckBox();

		accountInformationPage.fillName(input.get("first name"), input.get("last name"));

		accountInformationPage.fillCompanyName(input.get("company name"));

		accountInformationPage.fillCompanyAddress(input.get("company street"), input.get("company city"));

		accountInformationPage.selectCountry(input.get("country"));

		accountInformationPage.fillStateAndCity(input.get("State"), input.get("city"));

		accountInformationPage.fillZipcode(input.get("zipcode"));

		accountInformationPage.fillMobileNumber(input.get("mobile number"));

		accountInformationPage.clickCreateAccount();

		homePage.clickContinue();

		Boolean verifyLogInText = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[10]")).isDisplayed();
		softAssert.assertTrue(verifyLogInText);

		homePage.clickDeleteAccount();

		js.executeScript(
				"const elements = document.getElementsByClassName('GoogleActiveViewInnerContainer'); while (elements.length > 0) elements[0].remove()");

		Boolean accountDeletedMessage = driver.findElement(By.xpath("//h2[@class='title text-center']/b"))
				.isDisplayed();
		softAssert.assertTrue(accountDeletedMessage);

		homePage.clickContinue();

		softAssert.assertAll();

		tearDown();

	}

	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> testDataSetString = getData(
				"/src/test/java/seleniumframeworkdesign/Data/SubmitFormData.json");

		return new Object[][] { { testDataSetString.get(0) }, { testDataSetString.get(1) } };
	}

	@Test(dataProvider = "getLoginData")
	public void logIn(HashMap<String, String> input) throws IOException {

		HomePage homePage = launchApplication();

		SoftAssert softAssert = new SoftAssert();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		Boolean verifyHomePageBoolean = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[4]"))
				.isDisplayed();

		softAssert.assertTrue(verifyHomePageBoolean);

		homePage.clickSignUp();

		Boolean loginVerificationMessage = driver.findElement(By.xpath("//div[@class='login-form']/h2")).isDisplayed();
		softAssert.assertTrue(loginVerificationMessage);

		homePage.Login(input.get("emailid"), input.get("password"));

		homePage.Logout();

		Boolean verifyHomePage = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[4]")).isDisplayed();
		softAssert.assertTrue(verifyHomePage);

		softAssert.assertAll();

		tearDown();

	}

	@DataProvider
	public Object[][] getLoginData() throws IOException {

		List<HashMap<String, String>> data = getData("/src/test/java/seleniumframeworkdesign/Data/LogInData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

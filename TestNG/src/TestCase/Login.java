package TestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.UserPage;

public class Login {
	WebDriver driver;
	@AfterMethod(alwaysRun=true)
	public void am() {
		driver.quit();
	}
	@BeforeMethod(alwaysRun=true)
	public void bm() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://magento.com");
	}
	@Test(priority=10, groups= {"R","S"}, dataProvider="negative_data")
	public void Negative_login(String email, String pwd) {
		driver.findElement(HomePage.user_icon).click();
		driver.findElement(LoginPage.email_ip).sendKeys(email);
		driver.findElement(LoginPage.pwd_ip).sendKeys(pwd);
		driver.findElement(LoginPage.sign_in_buttn).click();
		Assert.assertEquals(driver.findElement(UserPage.error_msg).getText(), "Invalid login or password.");
	}
	@DataProvider(parallel=true)
	public Object[][] negative_data(){
		return new Object[][] {
			{"email1@gmail.com","password"},
			{"email2@gmail.com","password2"},
			{"email3@gmail.com","password3"}
		};
	}
	@Test(priority=5,groups= {"R"})
	public void positive_login() {
		driver.findElement(HomePage.user_icon).click();
		driver.findElement(LoginPage.email_ip).sendKeys("natarajan.ramanathan93@gmail.com");
		driver.findElement(LoginPage.pwd_ip).sendKeys("Welcome123");
		driver.findElement(LoginPage.sign_in_buttn).click();
		Assert.assertEquals(driver.findElement(UserPage.id).getText(), "ID: MAG003417822");
	}
	

}

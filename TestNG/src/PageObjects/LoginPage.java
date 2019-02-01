package PageObjects;

import org.openqa.selenium.By;

public class LoginPage {
	public static By email_ip= By.id("email");
	public static By pwd_ip=By.id("pass");
	public static By sign_in_buttn= By.id("send2");
	public static By error_msg= By.className("error-msg");
	public static By reg_btn= By.xpath("//span[text()='Register']");

}

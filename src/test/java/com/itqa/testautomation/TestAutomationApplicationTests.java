package com.itqa.testautomation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
class TestAutomationApplicationTests {

	public WebDriver driver;

	@BeforeAll
	public static void setDriver() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://themeselection.com/");

		Thread.sleep(3000);
	}

	@AfterEach
	public void quit() {
		driver.quit();
	}

	@Test
	void invalidEmail() throws InterruptedException {
		WebElement loginBtn = driver.findElement(By.id("menu-item-161"));
		loginBtn.click();

		Thread.sleep(1000);

		WebElement emailInput = driver.findElement(By.xpath("//input[@name=\"login_username\"]"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@name=\"login_password\"]"));

		emailInput.sendKeys("chamal");
		passwordInput.sendKeys("chamal@1234");

		WebElement signInBtn = driver.findElement(By.xpath("//*[@id=\"pp_login_3\"]/div/div[6]/input"));
		signInBtn.click();

		Thread.sleep(3000);

		WebElement error = driver.findElement(By.className("profilepress-login-status"));
		assertEquals(
				"Error: The username chamal is not registered on this site. If you are unsure of your username, try your email address instead.",
				error.getText());
	}

	@Test
	void invalidFakeEmail() throws InterruptedException {
		WebElement loginBtn = driver.findElement(By.id("menu-item-161"));
		loginBtn.click();

		Thread.sleep(1000);

		WebElement emailInput = driver.findElement(By.xpath("//input[@name=\"login_username\"]"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@name=\"login_password\"]"));

		emailInput.sendKeys("chamal@gmail.com");
		passwordInput.sendKeys("chamal@1234");

		WebElement signInBtn = driver.findElement(By.xpath("//*[@id=\"pp_login_3\"]/div/div[6]/input"));
		signInBtn.click();

		Thread.sleep(3000);

		WebElement error = driver.findElement(By.className("profilepress-login-status"));
		assertEquals("Unknown email address. Check again or try your username.", error.getText());
	}

	@Test
	void invalidPassWithRealUser() throws InterruptedException {
		WebElement loginBtn = driver.findElement(By.id("menu-item-161"));
		loginBtn.click();

		Thread.sleep(1000);

		WebElement emailInput = driver.findElement(By.xpath("//input[@name=\"login_username\"]"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@name=\"login_password\"]"));

		emailInput.sendKeys("dinithisandarekha@gmail.com");
		passwordInput.sendKeys("chamal@1234");

		WebElement signInBtn = driver.findElement(By.xpath("//*[@id=\"pp_login_3\"]/div/div[6]/input"));
		signInBtn.click();

		Thread.sleep(3000);

		WebElement error = driver.findElement(By.className("profilepress-login-status"));
		assertEquals(
				"Error: The password you entered for the email address dinithisandarekha@gmail.com is incorrect. Lost your password?",
				error.getText());
	}

	@Test
	void searchCount() throws InterruptedException {
		WebElement search = driver.findElement(By.xpath("//input[@type=\"search\"]"));
		search.sendKeys("react");

		WebElement searchBtn = driver.findElement(By.xpath("//button[@value='Search']"));
		searchBtn.click();

		Thread.sleep(5000);

		List<WebElement> cards = driver.findElements(By.xpath("//div[@class='product-card']"));
		assertTrue(cards.size() > 0);
	}

	@Test
	void newsLetter() throws InterruptedException {
		WebElement blog = driver.findElement(By.id("menu-item-42"));
		blog.click();

		Thread.sleep(3000);

		WebElement subscribeInput = driver.findElement(By.xpath("//*[@id=\"mailster-email-1\"]"));
		subscribeInput.sendKeys("dinsw98104@gmail.com");

		Thread.sleep(3000);
		WebElement subscribeButton = driver.findElement(By.xpath("//input[@Value='Subscribe']"));
		subscribeButton.submit();
	}
}

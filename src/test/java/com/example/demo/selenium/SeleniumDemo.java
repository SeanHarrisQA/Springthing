package com.example.demo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumDemo {

	private WebDriver driver;

	@BeforeEach
	void init() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// Creates new window
		this.driver = new ChromeDriver(options);
		// Maximise it
		this.driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

	@Test
	void turtleTest() {
		// redirects browser to this address
		this.driver.get("https://www.bbc.co.uk/search");
		WebElement search = this.driver.findElement(By.cssSelector("#search-input"));
		search.sendKeys("turtle");
		search.sendKeys(Keys.ENTER);

		WebElement result = this.driver.findElement(By.cssSelector(
				"#main-content > div.ssrcss-1v7bxtk-StyledContainer.enjd40x0 > div > div > ul > li:nth-child(1) > div > div > div.ssrcss-tq7xfh-PromoContent.e1f5wbog8 > div.ssrcss-1f3bvyz-Stack.e1y4nx260 > a"));

		assertEquals("The Turtle Dove Pilgrimage", result.getText());
	}

	@Test
	void penguinTest() throws InterruptedException {
		this.driver.get("https://www.bing.com");
		// Make the test wait so that the page can load, BING is shite whereas the bbc
		// is fast
		{
			Thread.sleep((long) 3000);
		}
		WebElement search = this.driver.findElement(By.cssSelector("#sb_form_q"));
		search.sendKeys("penguin");
		search.sendKeys(Keys.ENTER);

		{
			Thread.sleep((long) 3000);
		}

		WebElement result = this.driver
				.findElement(By.cssSelector("#b_results > li.b_algo.b_vtl_deeplinks > div.b_title > h2 > a"));

		assertEquals("Penguin Books UK | Official Website", result.getText());
	}

}

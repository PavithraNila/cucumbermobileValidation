package com.flipkart.stepdefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileValidationSteps {
	static WebDriver driver;
	static long startTime;
	static String name1;
	
	@Given("user launches flipkart applications")
	public void user_launches_flipkart_applications() {
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		  
		  driver.get("https://www.flipkart.com/");
		  driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	
	@Given("user login by entering valid credetials")
	public void user_login_by_entering_valid_credetials() {
		try {
			WebElement close=driver.findElement(By.xpath("//button[text()='✕']"));
			
			close.click();
		}
		catch(Exception e){
			//handle exception
		}
	   
	}

	@When("user Search mobile")
	public void user_Search_mobile() {
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("realme",Keys.ENTER);
	    
	}

	@When("user click on the mobile name")
	public void user_click_on_the_mobile_name() {
		WebElement mobileName1=driver.findElement(By.xpath("(//div[contains(text(),'realme')])[2]"));
		name1=mobileName1.getText();
		System.out.println(name1);
	    
	}

	@Then("user validate the mobilename")
	public void user_validate_the_mobilename() {
	   
		String parent= driver.getWindowHandle();
		Set<String> children=driver.getWindowHandles();
		for(String x:children) {
			if(!parent.equals(x)) {
				driver.switchTo().window(x);
			}
		}
		
		WebElement mobileName2=driver.findElement(By.xpath("//div[text()='₹7,499']"));
		String name2=mobileName2.getText();
		System.out.println(name2);
		
		Assert.assertTrue(mobileName2.isDisplayed());
		Assert.assertEquals(name1,name2);
	}
}

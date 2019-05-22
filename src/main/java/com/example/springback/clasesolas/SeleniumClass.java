package com.example.springback.clasesolas;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumClass {
	
	private WebDriver driver;
	 
	
	public SeleniumClass() {
		
	}
	public void chromeNavegador() {
		DesiredCapabilities capas = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.cl");
		
		try {
			//Thread.sleep(5000);
			//System.out.print(driver);
			driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")).sendKeys("Seba Hueco");
			driver.findElement(By.name("f")).submit();
			driver.navigate().to("https://www.bancoestado.cl/imagenes/_personas/home/default.asp");
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}
}

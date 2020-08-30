package br.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class tasksTest {
	
	public WebDriver acesso() throws MalformedURLException { 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to("http://172.20.0.1:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	@Test
	public void testDeveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acesso();
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		driver.findElement(By.id("saveButton")).click();
		String msg = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", msg);
		driver.quit();
	}
	
	@Test
	public void testNaoDeveSalvaTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acesso();
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		driver.findElement(By.id("saveButton")).click();
		String msg = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", msg);
		driver.quit();
	}
	
	@Test
	public void testNaoDeveSalvaTarefaSemData() throws MalformedURLException {
		WebDriver driver = acesso();
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		driver.findElement(By.id("saveButton")).click();
		String msg = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", msg);
		driver.quit();
	}
	

}

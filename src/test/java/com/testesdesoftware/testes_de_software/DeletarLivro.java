package com.testesdesoftware.testes_de_software;

import static org.hamcrest.MatcherAssert.assertThat;

// Generated by Selenium IDE

import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DeletarLivro {
  private WebDriver driver;
  // private Map<String, Object> vars;
  JavascriptExecutor js;

  private WaitForPageLoaded Loaded;
  
  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    // vars = new HashMap<String, Object>();
    Loaded = new WaitForPageLoaded(driver);
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void manterLivro() {
    //Login
    driver.get("https://ts-scel-web.herokuapp.com/login");
    driver.manage().window().setSize(new Dimension(1296, 1040));
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).sendKeys("jose");
    driver.findElement(By.name("password")).sendKeys("123");
    driver.findElement(By.cssSelector("button")).click();

    // dado que o livro não está cadastrado 
    Loaded.waitPage();
    assertThat(driver.findElement(By.linkText("Livros")).getText(), is("Livros"));
    driver.findElement(By.linkText("Livros")).click();
    Loaded.waitPage();
    assertThat(driver.findElement(By.cssSelector("h3:nth-child(2)")).getText(), is("Cadastrar Livro"));
    driver.findElement(By.id("isbn")).click();
    driver.findElement(By.id("isbn")).sendKeys("1234");
    driver.findElement(By.id("autor")).sendKeys("Carlos Nogueira");
    driver.findElement(By.id("titulo")).sendKeys("As Estrelas do Céu");
    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

    // dado que o livro já está cadastrado 
    Loaded.waitPage();
    assertThat(driver.findElement(By.xpath("//td[contains(.,'As Estrelas do Céu')]")).getText(), is("As Estrelas do Céu"));
    driver.findElement(By.xpath("//tr[td[contains(.,'As Estrelas do Céu')]]/td/div/a[@class='delete btn btn-sm btn-danger']")).click();
  }
}

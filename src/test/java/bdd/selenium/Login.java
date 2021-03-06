package bdd.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class Login {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

    /**
     *  firefox 46.0.1
     *
     * @throws Exception
     */
  @Before
  public void setUp() throws Exception {
          System.setProperty(
              "webdriver.chrome.driver",
              "D:\\_dev_tools\\chromedriver_win32\\chromedriver.exe");
    driver = new FirefoxDriver();

/*//      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//      capabilities.setCapability("chrome.binary", "C:\\Users\\yinghao_niu\\AppData\\Local\\360Chrome\\Chrome\\Application\\360chrome.exe");
//    driver = new ChromeDriver(capabilities);*/

//    driver = new ChromeDriver();
    baseUrl = "http://127.0.0.1:1234/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl + "/login");
    driver.findElement(By.id("j_username")).clear();
    driver.findElement(By.id("j_username")).sendKeys("STAR-85bfc7c8-4305-4928-a7d9-79d3325ba8be");
    driver.findElement(By.id("j_password")).clear();
    driver.findElement(By.id("j_password")).sendKeys("p");
    driver.findElement(By.name("login")).click();
  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

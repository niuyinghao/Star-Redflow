package bdd.steps;

import cucumber.api.Scenario;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest extends TestCase {
    protected WebDriver driver;
    protected String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    String templateId;

    public BaseTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "D:\\_dev_tools\\chromedriver_win32\\chromedriver.exe");
//        driver = new FirefoxDriver();


//        ChromeOptions options = new ChromeOptions();
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability("chrome.binary", "C:\\Users\\yinghao_niu\\AppData\\Local\\360Chrome\\Chrome\\Application\\360chrome.exe");
//        options.addArguments("window-size=1024,768");
//
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new ChromeDriver(capabilities);

        FirefoxProfile firefoxProfile = new ProfilesIni().getProfile("default");
        driver = new FirefoxDriver(firefoxProfile);

        baseUrl = "http://127.0.0.1:1234";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void afterStep(Scenario scenario) throws Exception {
        boolean failed = scenario.isFailed();

        File screenshotAs = ((FirefoxDriver) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File("target\\test\\screenshot\\" +
                this.getTemplateId() + ";" + scenario.getName() + ";" +
                scenario.getSourceTagNames() + ";" +
                scenario.getStatus() + ".jpg"));
        if (!failed) {
            driver.quit();
        }
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
/*
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.qa.dhgate.com:8888/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
*/

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            }
            else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public boolean equals(Object obj1, Object obj2) {
        return obj1.equals(obj2);
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

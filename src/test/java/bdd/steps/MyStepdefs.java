package bdd.steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Created by yinghao_niu on 2016/6/23 for project.
 */
public class MyStepdefs extends BaseTest{


    @After
    public void after(Scenario scenario) throws Exception {
        super.afterStep(scenario);
    }


    @Then("^the account register, user got login$")
    public void theAccountRegisterUserGotLogin() throws Throwable {
    }

    @And("^usename is auto generated$")
    public void usenameIsAutoGenerated(String abc) throws Throwable {
        System.out.println(abc);
    }

    @Given("^user in register page:$")
    public void userInRegisterPage() throws Throwable {
        driver.get(baseUrl + "/login");
        assertEquals("Star redflow", driver.getTitle());
        driver.findElement(By.linkText("注册")).click();
        assertEquals("Star redflow", driver.getTitle());
    }




    @Given("^user has login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userHasLoginWithAnd(String arg0, String arg1,DataTable data) throws Throwable {

        arg0 = data.getGherkinRows().get(0).getCells().get(0);
        arg1 = data.getGherkinRows().get(0).getCells().get(1);
        driver.get(baseUrl + "/login");
        assertEquals("Star redflow", driver.getTitle());
        driver.findElement(By.id("j_username")).clear();
        driver.findElement(By.id("j_username")).sendKeys(arg0);
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys(arg1);
        driver.findElement(By.id("rememberMe")).click();

        driver.findElement(By.name("login")).click();
        assertEquals("Star redflow", driver.getTitle());
    }

    @Given("^in the sea page$")
    public void inTheSeaPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^i click heart$")
    public void iClickHeart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the hear enter into age change$")
    public void theHearEnterIntoAgeChange() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^input \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"; with \"([^\"]*)\"$")
    public void inputWith(String arg0, String arg1, String arg2, String arg3) throws Throwable {
        setTemplateId(arg3);
        driver.findElement(By.id("signupForm:password")).clear();
        driver.findElement(By.id("signupForm:password")).sendKeys(arg0);
        driver.findElement(By.id("signupForm:confirmPassword")).clear();
        driver.findElement(By.id("signupForm:confirmPassword")).sendKeys(arg1);
        driver.findElement(By.id("signupForm:email")).clear();
        driver.findElement(By.id("signupForm:email")).sendKeys(arg2);
        driver.findElement(By.id("signupForm:save")).click();
        assertFalse(equals("Star redflow",driver.getTitle()));
    }
}
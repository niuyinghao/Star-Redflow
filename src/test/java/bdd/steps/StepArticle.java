package bdd.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import my.model.persist.board.Article;
import my.service.ArticleManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by niuyinghao on 2016/4/22 for project.
 */

public class StepArticle {

    @Autowired
    private ArticleManager articleManager;

    @Given("^in main page$")
    public void inMainPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Then("^navigate to number of articles published$")
    public void navigateToNumberOfArticlesPublished() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Given("^user has red article, the red article show pop in the position queue$")
    public void userHasRedArticleTheRedArticleShowPopInThePositionQueue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Given("^a feedback button on the tool bar$")
    public void aFeedbackButtonOnTheToolBar() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @When("^click the feedback button , then show a dialog$")
    public void clickTheFeedbackButtonThenShowADialog() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Then("^user input feedback$")
    public void userInputFeedback() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Then("^send the feedback$")
    public void sendTheFeedback() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Then("^refresh feedback history content$")
    public void refreshFeedbackHistoryContent() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

    }

    @Given("^具有管理员权限$")
    public void 具有管理员权限() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }

    @Then("^进入添加文章界面$")
    public void 进入添加文章界面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }

    @When("^点击添加文章按钮$")
    public void 点击添加文章按钮() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }

    @Then("^跳转到添加文章界面$")
    public void 跳转到添加文章界面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }

    @When("^录入文章内容$")
    public void 录入文章内容() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Article article = new Article();
        article.setContent("this  is article");
        articleManager.save(article);
    }

    @And("^点击提交按钮$")
    public void 点击提交按钮() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }

    @Then("^添加了一个新文章$")
    public void 添加了一个新文章() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        // throw new PendingException();

    }
}

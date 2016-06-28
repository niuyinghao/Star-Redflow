package bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by niuyinghao on 2016/5/12 for project.
 */
@ContextConfiguration("classpath:**/applicationContext.xml")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\java\\bdd\\feature\\place.feature"},
        glue = {"com.cdi.igs.adapter.cucumber", "cucumber.api.spring", "bdd.steps"},
//        tags = {"@test"},
        format = {"json:target/integration_cucumber.json"}
)
public class Runner {
}

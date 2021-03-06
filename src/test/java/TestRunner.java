import com.codeborne.selenide.*;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.GetProperties;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = "stepdefs",
        tags = "@all",
        features = {"src/test/resources/features/"}
        )
public class TestRunner {
    @BeforeClass
    public static void setUp() {
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }
}

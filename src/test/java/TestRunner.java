import com.codeborne.selenide.*;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.GetProperties;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Cucumber.class)
//@CucumberOptions(
//        monochrome = true,
//        glue = "stepdefs",
//        tags = GetProperties.getInstance().getProperty("url"),
//        features = {"src/test/resources/features/"})
public class TestRunner {
    @BeforeClass
    public static void setUp() {
        System.setProperty(
                "cucumber.options",
                "--monochrome true" +
                "--glue stepdefs" +
                "--tags ~" + GetProperties.getInstance().getProperty("test") +
                "--features src/test/resources/features/"
        );
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }
}

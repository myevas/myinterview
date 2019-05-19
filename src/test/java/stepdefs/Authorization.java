package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;

public class Authorization {
    LoginPage loginPage = new LoginPage();

    @Given("^Open login page \"([^\"]*)\"$")
    public void openLoginPage(String loginPage) throws Throwable {
        open(loginPage);
    }

    @And("^user press button \"([^\"]*)\"$")
    public void user_press_button(String arg1) throws Throwable {
        loginPage.signIn();
    }
}

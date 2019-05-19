package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.Checks;
import pages.LoginPage;


public class Common {
    LoginPage loginPage = new LoginPage();
    Checks checks = new Checks();
    @And("^User fill field with the parameters \"([^\"]*)\" \"([^\"]*)\"$")
    public void user_fill_field_with_the_parameters(String field, String value) throws Throwable {
        loginPage.enterField(field, value);
    }

    @And("^Check open displayed table$")
    public void check_open_displayed_table() throws Throwable {
        checks.checkTableWithPlayers();
    }

    @Given("^Check open main page$")
    public void check_open_main_page() throws Throwable {
        checks.checkLoadMainPage();
    }


}

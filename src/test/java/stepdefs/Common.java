package stepdefs;

import asserts.TestAsserts;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.LoginPage;


public class Common {
    LoginPage loginPage = new LoginPage();
    TestAsserts testAsserts = new TestAsserts();
    @And("^User fill field with the parameters \"([^\"]*)\" \"([^\"]*)\"$")
    public void user_fill_field_with_the_parameters(String field, String value) throws Throwable {
        loginPage.enterField(field, value);
    }

    @And("^Check open displayed table$")
    public void check_open_displayed_table() throws Throwable {
        testAsserts.checkTableWithPlayers();
    }

    @Given("^Check open main page$")
    public void check_open_main_page() throws Throwable {
        testAsserts.checkLoadMainPage();
    }


}

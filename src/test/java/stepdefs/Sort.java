package stepdefs;

import asserts.TestAsserts;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.MainPage;
import pages.PlayersPage;

public class Sort {
    MainPage mainPage = new MainPage();
    PlayersPage playersPage = new PlayersPage();
    TestAsserts testAsserts = new TestAsserts();
    @Given("^Open players list$")
    public void open_players_list() throws Throwable {
        mainPage.moveMouseToUsers();
        mainPage.clickToUsers();
    }

    @Given("^Sort by name$")
    public void sort_by_name() throws Throwable {
        playersPage.sortByUserName();
    }

    @And("^Check result$")
    public void check_result() throws Throwable {
        testAsserts.checkSortResult();
    }
}

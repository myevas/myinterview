package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.Checks;
import pages.MainPage;
import pages.PlayersPage;

public class Sort {
    MainPage mainPage = new MainPage();
    PlayersPage playersPage = new PlayersPage();
    Checks checks = new Checks();
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
        checks.checkSortResult();
    }
}

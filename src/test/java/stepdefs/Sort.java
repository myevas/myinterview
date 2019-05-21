package stepdefs;

import asserts.TestAsserts;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.MainPage;
import pages.PlayersPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
    MainPage mainPage = new MainPage();
    PlayersPage playersPage = new PlayersPage();
    TestAsserts testAsserts = new TestAsserts();
    List<String> listNameBeforeSorting = new ArrayList<>();
    String[] listNameArray;
    @Given("^Open players list$")
    public void open_players_list() throws Throwable {
        mainPage.moveMouseToUsers();
        mainPage.clickToUsers();
    }

    @And("^Check result$")
    public void check_result() throws Throwable {
        testAsserts.checkSortResult(listNameArray);
    }

    @And("^Click to show all record and sort$")
    public void сlick_to_show_all_record() throws Throwable {
        playersPage.clickShowAllRecordsAndSort();
        listNameBeforeSorting = playersPage.getAllNames();
        listNameArray = listNameBeforeSorting.toArray(new String[listNameBeforeSorting.size()]);
        Arrays.sort(listNameArray);
        playersPage.sortByUserName();

    }




}

package asserts;

import helpers.DataFileHelper;
import helpers.GetProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.PlayersPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static helpers.ParserExpectedResult.parseFile;

public class TestAsserts {
    PlayersPage playersPage = new PlayersPage();
    private By tableUsers = By.xpath("//table/thead//a[contains(text(),'Username')]");
    String[] listNameArrayAfterSorting;

    public void checkLoadMainPage() {
        String title = title();
        Assert.assertEquals("Dashboard - Casino", title);
    }

    public void checkTableWithPlayers() {
        $(tableUsers).shouldHave(text("Username"));
    }

    public void checkSortResult(String[] listNameArray) throws IOException {
        List<String> listNameAfterSorting = playersPage.getAllNames();
        listNameArrayAfterSorting = listNameAfterSorting.toArray(new String[listNameAfterSorting.size()]);
        Assert.assertTrue(Arrays.equals(listNameArray,listNameArrayAfterSorting));
    }
}

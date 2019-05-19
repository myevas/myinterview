package pages;

import helpers.DataFileHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.io.IOException;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static helpers.ParserExpectedResult.parseFile;

public class Checks {
    PlayersPage playersPage = new PlayersPage();
    private By tableUsers = By.xpath("//table/thead//a[contains(text(),'Username')]");

    public void checkLoadMainPage() {
        String title = title();
        Assert.assertEquals("Dashboard - Casino", title);
    }

    public void checkTableWithPlayers() {
        $(tableUsers).shouldHave(text("Username"));
    }

    public void checkSortResult() throws IOException {
        Assert.assertTrue(parseFile(DataFileHelper.read("\\src\\test\\resources\\ExpectedResult.txt")).
                equals(playersPage.getAllNames()));
    }
}

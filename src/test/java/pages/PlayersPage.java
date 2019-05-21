package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class PlayersPage {
    private By sortByUserName = By.xpath("//a[text()='Username']");
    private By listUsers = By.xpath("//tbody/tr/td[2]/a");
    private By select = By.xpath("//select[@name='pageSizePlayers']");
    private By option = By.xpath("//option[@value='500']");
    private By loading = By.xpath("//div[@class='grid-view grid-view-loading']");


    public void sortByUserName(){

        $(sortByUserName).click();
        //TODO waiter loading element
        sleep(5000);

    }

    public List<String> getAllNames(){
        return $$(listUsers).texts();
    }

    public void clickShowAllRecordsAndSort(){
        $(select).click();
        $(option).click();
        //TODO waiter loading element
        sleep(5000);
    }

}

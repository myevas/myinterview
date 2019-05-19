package pages;

import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlayersPage {
    private By sortByUserName = By.xpath("//a[text()='Username']");
    private By listUsers = By.xpath("//tbody/tr/td[2]/a");

    public void sortByUserName(){
        $(sortByUserName).click();
    }

    public List<String> getAllNames(){
        return $$(listUsers).texts();
    }

}

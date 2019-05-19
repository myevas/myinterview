package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private By users = By.xpath("//span[text()='Users']");
    private By usersLink = By.xpath("//a[@href='/user/player/admin']");

    public void moveMouseToUsers(){
        $(users).scrollTo().click();
    }

    public void clickToUsers() {
        $(usersLink).click();
    }
}
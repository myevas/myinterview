package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private By login = By.id("UserLogin_username");
    private By password = By.id("UserLogin_password");
    private By signInButton = By.xpath("//input[@value='Sign in']");

    public void enterField(String field, String value) {
        switch(field){
            case "Login": $(this.login).sendKeys(value);
            break;
            case "Password": $(this.password).sendKeys(value);
            break;
        }
    }

    public void signIn() {
        $(signInButton).click();
    }
}

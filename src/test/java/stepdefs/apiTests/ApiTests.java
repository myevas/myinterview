package stepdefs.apiTests;

import com.google.gson.JsonObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import helpers.GetProperties;
import helpers.RandomDataHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Base64;

public class ApiTests {
    String url = GetProperties.getInstance().getProperty("url");
    String s = GetProperties.getInstance().getProperty("username");
    String headerAuthorization = "Basic " + Base64.getUrlEncoder().withoutPadding().encodeToString(GetProperties.getInstance().getProperty("username").getBytes());
    String pathForToken = GetProperties.getInstance().getProperty("pathForToken");
    String contentTypeJson = GetProperties.getInstance().getProperty("Content-Type");
    String tokenGuest = "";
    String registrationName = RandomDataHelper.randomString(10);
    String registrationPassword = RandomDataHelper.randomString(10);

    @Given("^Get guest token$")
    public void get_guest_token() throws Throwable {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type","client_credentials");
        jsonObject.addProperty("scope","guest:default");
        String body = jsonObject.toString();
        Response response = RestAssured.given()
                .log()
                .all()
                .baseUri(url)
                .basePath(pathForToken)
                .headers("Authorization", headerAuthorization)
                .contentType(contentTypeJson)
                .body(body)
                .when()
                .post()
                .then()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200);
        tokenGuest = response.path("access_token").toString();
    }

    @Given("^Registration player and check response$")
    public void registration_player_and_check_response() throws Throwable {
        System.out.println(tokenGuest);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type","client_credentials");
        jsonObject.addProperty("scope","guest:default");
        String body = jsonObject.toString();
    }

    @Given("^Authorization player$")
    public void authorization_player() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Get created profile player and check response$")
    public void get_created_profile_player_and_check_response() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Get another profile player$")
    public void get_another_profile_player() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

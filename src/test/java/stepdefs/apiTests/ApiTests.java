package stepdefs.apiTests;

import com.google.gson.JsonObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import helpers.GetProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ApiTests {
    GetProperties prop = new GetProperties();
    String url = prop.getProperty("url");
    String s = prop.getProperty("username");
    String headerAuthorization = "Basic " + Base64.getUrlEncoder().withoutPadding().encodeToString(prop.getProperty("username").getBytes());
    String pathForToken = prop.getProperty("pathForToken");
    String contentTypeJson = prop.getProperty("Content-Type");
    String tokenGuest = "front_2d6b0a8391742f5d789d7d915755e09e";


    @Given("^Get guest token$")
    public void get_guest_token() throws Throwable {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type","client_credentials");
        jsonObject.addProperty("scope","guest:default");
        String body = jsonObject.toString();
        given()
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
                .statusCode(200);
        //TODO In the future, figure out how to get a token
        //tokenGuest = response.path("access_token").toString();
    }

    @Given("^Registration player and check response$")
    public void registration_player_and_check_response() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
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

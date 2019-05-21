package stepdefs.apiTests;

import com.google.gson.JsonObject;
import cucumber.api.java.en.When;
import helpers.GetProperties;
import helpers.RandomDataHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Base64;

public class ApiTests {
    private String tokenGuest = "";
    private String tokenPlayer = "";
    private Integer idPlayer;
    private String url = GetProperties.getInstance().getProperty("url");
    private String pathForPlayers = GetProperties.getInstance().getProperty("pathForPlayers");
    private String headerAuthorization = "Basic " + Base64.getUrlEncoder().withoutPadding().encodeToString(GetProperties.getInstance().getProperty("username").getBytes());
    private String pathForToken = GetProperties.getInstance().getProperty("pathForToken");
    private String contentTypeJson = GetProperties.getInstance().getProperty("Content-Type");
    private String registrationLogin = RandomDataHelper.randomString("",10);
    private String registrationName = RandomDataHelper.randomString("name",5);
    private String registrationSurname = RandomDataHelper.randomString("name",5);
    private String registrationPassword = Base64.getUrlEncoder().encodeToString(RandomDataHelper.randomString("",10).getBytes());

    @When("^Get guest token$")
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

    @When("^Registration player and check response$")
    public void registration_player_and_check_response() throws Throwable {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username",registrationLogin);
        jsonObject.addProperty("password_change",registrationPassword);
        jsonObject.addProperty("password_repeat",registrationPassword);
        jsonObject.addProperty("email",registrationName + "@example.com");
        jsonObject.addProperty("name",registrationName);
        jsonObject.addProperty("surname", registrationSurname);
        String body = jsonObject.toString();
        Response response = RestAssured.given()
                .log()
                .all()
                .baseUri(url)
                .basePath(pathForPlayers)
                .headers("Authorization", "Bearer " + tokenGuest)
                .contentType(contentTypeJson)
                .body(body)
                .when()
                .post()
                .then()
                .log()
                .all()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 201);
        idPlayer = Integer.parseInt(response.path("id").toString());
    }

    @When("^Authorization player$")
    public void authorization_player() throws Throwable {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type","password");
        jsonObject.addProperty("username",registrationLogin);
        jsonObject.addProperty("password",registrationPassword);
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
        tokenPlayer = response.path("access_token").toString();
    }

    @When("^Get created profile player and check response$")
    public void get_created_profile_player_and_check_response() throws Throwable {
        Response response = RestAssured.given()
                .log()
                .all()
                .baseUri(url)
                .basePath(pathForPlayers + "/" + idPlayer)
                .headers("Authorization", "Bearer " + tokenPlayer)
                .contentType(contentTypeJson)
                .when()
                .get()
                .then()
                .log()
                .all()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200);
        idPlayer++;
    }

    @When("^Get another profile player$")
    public void get_another_profile_player() throws Throwable {
        Response response = RestAssured.given()
                .log()
                .all()
                .baseUri(url)
                .basePath(pathForPlayers + "/" + idPlayer)
                .headers("Authorization", "Bearer " + tokenPlayer)
                .contentType(contentTypeJson)
                .when()
                .get()
                .then()
                .log()
                .all()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 404);
    }
}

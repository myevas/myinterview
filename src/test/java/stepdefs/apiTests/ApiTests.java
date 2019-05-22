package stepdefs.apiTests;

import com.google.gson.JsonObject;
import cucumber.api.java.en.When;
import gherkin.lexer.Da;
import helpers.DataFileHelper;
import helpers.GetProperties;
import helpers.RandomDataHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.JsonAssert;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Base64;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

import static net.javacrumbs.jsonunit.core.Option.COMPARING_ONLY_STRUCTURE;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_VALUES;

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
        String body = DataFileHelper.read(GetProperties.getInstance().getProperty("postGuestAuth"));
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
        JSONObject jsonObj = new JSONObject(DataFileHelper.read(GetProperties.getInstance().getProperty("postRegPlayer")));
        jsonObj.put("username",registrationLogin);
        jsonObj.put("password_change",registrationPassword);
        jsonObj.put("password_repeat",registrationPassword);
        jsonObj.put("email",registrationName + "@example.com");
        jsonObj.put("name",registrationName);
        jsonObj.put("surname", registrationSurname);
        String body = jsonObj.toString();
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
        String jsonResponse = response.getBody().asString();
        System.out.println(jsonResponse);
        String jsonExpectedResult = DataFileHelper.read(GetProperties.getInstance().getProperty("regExpectedNewPlayer"));
        JsonAssert.setOptions(COMPARING_ONLY_STRUCTURE);
        assertJsonEquals(jsonResponse, jsonExpectedResult);
    }

    @When("^Authorization player$")
    public void authorization_player() throws Throwable {
        JSONObject jsonObj = new JSONObject(DataFileHelper.read(GetProperties.getInstance().getProperty("postRegPlayer")));
        jsonObj.put("grant_type","password");
        jsonObj.put("username",registrationLogin);
        jsonObj.put("password",registrationPassword);
        String body = jsonObj.toString();
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
        String jsonResponse = response.getBody().asString();
        System.out.println(jsonResponse);
        String jsonExpectedResult = DataFileHelper.read(GetProperties.getInstance().getProperty("getExpectedNewPlayer"));
        JsonAssert.setOptions(COMPARING_ONLY_STRUCTURE);
        assertJsonEquals(jsonResponse, jsonExpectedResult);
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

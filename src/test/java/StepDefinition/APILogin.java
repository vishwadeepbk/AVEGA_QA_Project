package StepDefinition;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class APILogin {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    String endpoint;
    Response response;

    @Given("User sets the API endpoint {string}")
    public void user_sets_the_api_endpoint(String apiEndpoint) {

        endpoint = apiEndpoint;
    }

    @When("User sends a GET request")
    public void sendGetRequest() {

        response = RestAssured
                .given()
                .when()
                .get(BASE_URL + endpoint);
    }

    @Then("Response status code should be {int}")
    public void validateStatusCode(int statusCode) {

        response.then().statusCode(statusCode);
    }

    @Then("Response should contain title")
    public void validateTitle() {

        response.then().body("title", notNullValue());
    }

    @When("User sends a POST request with title {string} and body {string}")
    public void createPost(String title, String bodyText) {

        String body = "{"
                + "\"title\":\"" + title + "\","
                + "\"body\":\"" + bodyText + "\","
                + "\"userId\":1"
                + "}";

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(BASE_URL + endpoint);
    }
}
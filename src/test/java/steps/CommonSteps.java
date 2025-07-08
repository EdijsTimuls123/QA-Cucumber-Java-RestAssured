package steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.*;

public class CommonSteps {

  private Response response;

  @Then("the response status should be 200")
  public void validateStatus200() {
    assertThat(response)
            .as("Response should not be null for %s")
            .isNotNull();

    assertThat(response.statusCode())
            .as("Expected HTTP 200 when %s but got %d. Body: %s",
                    response.statusCode(), response.getBody().asString())
            .isEqualTo(200);
  }

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }
}

package steps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BaseApiClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

  private final CommonSteps commonSteps;

  public LoginSteps(CommonSteps commonSteps) {
    this.commonSteps = commonSteps;
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);
  private static final String LOGIN_ENDPOINT = "/rest/public/login";
  private static final String USERNAME = "testuser@qa.com";
  private static final String PASSWORD = "Parole123";

  @Given("the user logs in with username {string} and password {string}")
  public void login(String username, String password) {
    String payload = String.format("{\"name\":\"%s\",\"password\":\"%s\"}", username, password);

    LOGGER.info("Attempting login to {} with user {}", LOGIN_ENDPOINT, USERNAME);

    Response response = BaseApiClient.post(LOGIN_ENDPOINT, payload);
    commonSteps.setResponse(response);

    assertThat(response.statusCode())
            .as("Login failed. Expected status 200 but got %s. Response body: %s", response.statusCode(), response.getBody().asString())
            .isEqualTo(200);

    extractAndStoreSessionContext(response);
    LOGGER.info("Login successful. Session context initialized.");
  }

  private void extractAndStoreSessionContext(Response response) {
    String csrfToken = response.header("_csrf");
    assertThat(csrfToken)
            .as("CSRF token not found in login response headers")
            .isNotBlank();

    Map<String, String> cookies = response.getCookies();
    assertThat(cookies)
            .as("Cookies not found in login response")
            .isNotEmpty();

    SessionContext.setCsrfToken(csrfToken);
    SessionContext.setCookies(cookies);

    LOGGER.debug("CSRF token set: {}", csrfToken);
    LOGGER.debug("Session cookies: {}", cookies);
  }
}
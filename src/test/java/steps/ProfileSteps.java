package steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static utils.PayloadBuilder.buildDefaultPayload;

import utils.BaseApiClient;

public class ProfileSteps {

  private final CommonSteps commonSteps;

  public ProfileSteps(CommonSteps commonSteps) {
    this.commonSteps = commonSteps;
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(ProfileSteps.class);
  private static final String ACCOUNT_ENTRIES_ENDPOINT = "/rest/public/profile/account-entries";

  private static final int DEFAULT_PAGE = 1;
  private static final int DEFAULT_PAGE_SIZE = 9;

  private Response response;

  @When("the user fetches profile account entries")
  public void fetchAccountEntries() {
    Map<String, Object> payload = buildDefaultPayload(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);

    try {
      response = BaseApiClient.postCsrf(ACCOUNT_ENTRIES_ENDPOINT, payload);
      commonSteps.setResponse(response);
      LOGGER.info("Successfully fetched profile account entries");
    } catch (Exception e) {
      LOGGER.error("Error fetching profile account entries", e);
      throw e;
    }
  }

  @Then("the openingBalance should be {float}")
  public void verifyOpeningBalance(float expectedBalance) {
    try {
      float actualBalance = response.jsonPath().getFloat("openingBalance");

      assertThat(actualBalance)
              .as("Expected openingBalance to be %s but was %s", expectedBalance, actualBalance)
              .isEqualTo(expectedBalance);

      LOGGER.info("Opening balance verification passed: {}", actualBalance);
    } catch (AssertionError ae) {
      LOGGER.error("Assertion failed for openingBalance: {}", ae.getMessage());
      throw ae;
    } catch (Exception e) {
      LOGGER.error("Unexpected error verifying openingBalance", e);
      throw e;
    }
  }
}
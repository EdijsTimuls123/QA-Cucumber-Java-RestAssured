package utils;

import config.Config;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.SessionContext;

import static io.restassured.RestAssured.*;

public class BaseApiClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseApiClient.class);

  public static Response postCsrf(String endpoint, Object payload) {
    LOGGER.info("POST {} with payload: {}", endpoint, payload);

    try {
      return given()
              .baseUri(Config.BASE_URL)
              .accept("application/json")
              .contentType("application/json")
              .cookies(SessionContext.getCookies())
              .header("x-xsrf-token", SessionContext.getCsrfToken())
              .body(payload)
              .post(endpoint)
              .then()
              .extract()
              .response();

    } catch (Exception e) {
      LOGGER.error("Error during POST to {}: {}", endpoint, e.getMessage());
      throw e;
    }
  }

  public static Response post(String endpoint, Object payload) {
    try {
      return given()
              .baseUri(Config.BASE_URL)
              .accept("application/json")
              .contentType("application/json")
              .body(payload)
              .post(endpoint)
              .then()
              .extract()
              .response();

    } catch (Exception e) {
      LOGGER.error("Error during POST to {}: {}", endpoint, e.getMessage());
      throw e;
    }
  }
}

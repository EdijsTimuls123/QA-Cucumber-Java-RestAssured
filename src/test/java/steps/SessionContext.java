package steps;

import java.util.Map;

public class SessionContext {
  private static Map<String, String> cookies;

  private static String csrfToken;

  public static void setCookies(Map<String, String> cookies) {
    SessionContext.cookies = cookies;
  }

  public static Map<String, String> getCookies() {
    return cookies;
  }

  public static void setCsrfToken(String token) {
    csrfToken = token;
  }

  public static String getCsrfToken() {
    return csrfToken;
  }
}
Feature: Login and verify user balance

  @QA @Test
  Scenario Outline: Login and verify openingBalance for different users
    Given the user logs in with username "<username>" and password "<password>"
    Then the response status should be 200
    When the user fetches profile account entries
    Then the response status should be 200
    Then the openingBalance should be <openingBalance>

    Examples:
      | username         | password   | openingBalance |
      | testuser@qa.com  | Parole123  | 0.00           |
#       Future usage
#      | user2@example.com| Pass456    | 100.50         |
#      | user3@example.com| Secret789  | 50.75          |

Feature: HarryPotter WebSite API Tests

#  @HarryPotter
  Scenario: Verify sorting hat
    Given Send a get request to "sortingHat" Request includes
    When Verify that status code be 200, and content type should be "application/json; charset=utf-8"
    Then Verify that response body contains one of the following houses
      | "Gryffindor" | "Ravenclaw" | "Slytherin" | "Hufflepuff" |

#  @HarryPotter
  Scenario:  Verify sorting hat with Negative
    Given Send a get request to "sortingHat" Request includes
    When Verify that status code should not be 401, and content type should be "application/json; charset=utf-8"

#  @HarryPotter
  Scenario: Verify bad key
    Given Send a get request to "characters" Request with "token" and Header Accepts value "application/json"
    When Query param key with value "invalid"
    Then Verify status code 401, content type "application/json; charset=utf-8"
    Then Verify response status line include message "Unauthorized"
    Then Verify that response body says "\"error\":\"API Key Not Found\""

#  @HarryPotter
  Scenario: Verify no key
    Given Send a get request to "characters" Request with "" and Header Accepts value "application/json"
    Then Verify status code 409, content type "application/json; charset=utf-8"
    Then Verify response status line include message "Conflict"
    Then Verify that response body says "\"error\":\"Must pass API key for request\""

  @HarryPotter
  Scenario: Verify number of characters
    Given Send a get request to "characters" Request with "token" and Header Accepts value "application/json"
    Then Verify status code 200, content type "application/json; charset=utf-8"
    Then Verify response contains 195 characters
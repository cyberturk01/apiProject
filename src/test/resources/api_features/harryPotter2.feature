Feature: Harry Potter 2. Page

  Scenario: Verify number of character id and house
    Given Send a get request to "characters" Request with "token" and Header Accepts value "application/json"
    When Query param key with value "token"
    Then Verify status code 200, content type "application/json; charset=utf-8"
    Then Verify all characters in the response have id field which is not empty
    Then Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
    Then Verify value of the house in all characters in the response is one of the following:
      | "Gryffindor" | "Ravenclaw" | "Slytherin" | "Hufflepuff<!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->

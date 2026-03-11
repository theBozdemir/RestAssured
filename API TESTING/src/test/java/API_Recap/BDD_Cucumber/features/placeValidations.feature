Feature: Validating Place API's
  Scenario: Verify if place is been succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with Post http request
    Then the API call is success with status code
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
    |name   |language|address           |
    |AAhouse|English |World Cross Center|
    |BBhouse|Spanish |Sea Cross Center|
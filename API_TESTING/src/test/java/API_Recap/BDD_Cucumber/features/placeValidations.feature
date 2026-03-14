Feature: Validating Place API's

  @AddPlaceRecap
  Scenario Outline: Verify if place is been succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address            |
      | AAhouse | English  | World Cross Center |
  #  |BBhouse|Spanish |Sea Cross Center|

  @DeletePlace
  Scenario: If Delete Place functionality working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "DELETE" http request
    And "status" in response body is "OK"



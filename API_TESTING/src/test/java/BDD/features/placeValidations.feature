Feature: Validating Place API's

  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user call "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name        | language | address            |
      | Tarik House | English  | World Cross Center |
    #  | Sevs House  | French   | 50 Grand Avenue    |
Feature: Validating Weather API's
  Scenario: Verify the searched cities weather
    Given Weather API payload with "<city>"
    When User calls "GetWeatherAPI" with Get http request

    Examples:
    |city|
    |Toronto|
    |london|
    |New York|
    |Istanbul|
    |Paris|
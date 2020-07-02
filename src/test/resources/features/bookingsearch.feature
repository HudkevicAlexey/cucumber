Feature: Booking search
  Scenario: Search
    Given Keyword for search is "Grand Hyatt"
    When User does search
    Then "Grand Hyatt Dubai" is displayed on the first page
    And Score of "Grand Hyatt Dubai" is "9.0"
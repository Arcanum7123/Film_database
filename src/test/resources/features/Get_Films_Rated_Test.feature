Feature: Get films rated
  AS A user
  I WANT to see all films with a specific rating
  SO THAT I can select an appropriate film

  Scenario: User GETs films rated R
    When I call /rated/R
    Then I receive a list of all films rated R
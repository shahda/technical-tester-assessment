@RunAllScenarios
Feature: StarGroup
  @findFixtureById
  Scenario Outline: Get Fixtures by ID
    Given the app is up and running
    When the request is performed using fixture id "<id>"
    And the response includes the correct response "<id>" and status code

    Examples:
      | id  |
      | 4   |
      | 2   |
      | 3   |
      | 1   |

  @getAllFixtures
   Scenario: Get all Fixtures
    Given the app is up and running
    When the request is performed to get all fixtures
    Then response should be 200
    And the response includes the correct fixture size 3
    And each fixture has fixtureid value

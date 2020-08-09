@RunAllGetScenarios
Feature: Get All Fixtures and Search by Fixture ID

    Background:
    Given the app is up and running

  @getAllFixtures
  Scenario: Get all Fixtures
    When the request is performed to get all fixtures
    Then response should be 200
    And the response includes the correct fixture size 3
    And each fixture has fixtureid value

  @findFixtureById
  Scenario Outline: Get Fixtures by ID
    When the request is performed using fixture id "<id>"
    And the response includes the correct response "<id>" and status code
    Examples:
      | id  |
      | 1   |
      | 2   |
      | 3   |





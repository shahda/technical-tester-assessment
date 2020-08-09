@RunAllPostScenarios
Feature: Post new Fixture and Delete a fixture

  Background:
    Given the app is up and running

@StoreFixtureDeleteFixture
Scenario Outline: Store Fixture with Unique Fixture Id and retrive it and than delete it
When the request is performed to store new fixture with using "<TEST_REF>" and fixture ID "<id>"
Then response should be 200
And Fixture should be added successfully with message "Fixture has been added"
When the request is performed using new fixture id "<id>"
Then the response includes the correct response "<id>" and status code
And delete the new  fixture "<id>"
Then response should be 200
Then the correct response message "Fixture has been deleted" should be received
When the request is performed using fixture id "<id>"
And the response should be "Fixture not found" and Status code 404

Examples:
| TEST_REF  | id |
| TC_01     |  4 |
| TC_02     |  5 |

  @StoreFixtureRetriveFixture
  Scenario Outline: Store Fixture with Unique Fixture Id and assert Team ID
  When the request is performed to store new fixture with using "<TEST_REF>" and fixture ID "<id>"
  Then response should be 200
  And Fixture should be added successfully with message "Fixture has been added"
  When the request is performed using new fixture id "<id>"
  Then the response includes the correct response "<id>" and status code
  And verify team having "HOME" as first item in teamId

    Examples:
    | TEST_REF  | id |
    | TC_01     |  4 |
    | TC_02     |  5 |

  @DeleteFixture
  Scenario Outline: Delete Existing Fixture by ID
  When the delete request is performed using fixture id "<id>"
  Then response should be 200
  Then the correct response message "Fixture has been deleted" should be received
  When the request is performed using deleted fixture id "<id>"
  And the response should be "Fixture not found" and Status code 404
  Examples:
      | id |
      |  4 |
      |  5 |
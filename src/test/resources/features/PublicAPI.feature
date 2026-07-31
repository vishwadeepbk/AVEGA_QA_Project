Feature: JSONPlaceholder API Testing

  Scenario: Get a post successfully
    Given User sets the API endpoint "/posts/1"
    When User sends a GET request
    Then Response status code should be 200
    And Response should contain title

  Scenario: Create a new post
    Given User sets the API endpoint "/posts"
    When User sends a POST request with title "Automation Testing" and body "Rest Assured"
    Then Response status code should be 201

  Scenario: Get invalid post
    Given User sets the API endpoint "/posts/99999"
    When User sends a GET request
    Then Response status code should be 404
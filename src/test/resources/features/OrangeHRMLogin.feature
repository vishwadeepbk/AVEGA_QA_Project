Feature: OrangeHRM Login Functionality

  Background:
    Given User launches the Chrome browser
    And User navigates to OrangeHRM login page

  Scenario: Verify successful login and logout
    When User enters valid username "Admin"
    And User enters valid password "admin123"
    And User clicks on Login button
    Then User should be redirected to Dashboard
    When User logs out
    Then User should be redirected to Login page

  Scenario: Verify login with invalid credentials
    When User enters valid username "Admins"
    And User enters valid password "admin123s"
    And User clicks on Login button
    Then User should see "Invalid credentials" message
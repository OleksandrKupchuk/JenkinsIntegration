Feature: Login

  @regression
  Scenario: Login
    When [UI] User login
    Then [UI] User should be on dashboard page

  Scenario Outline: Login with parameters
    When [UI] User login with <username> and <password>
    Then [UI] User should be on dashboard page
    Examples:
      | username | password |
      | Admin    | admin123 |

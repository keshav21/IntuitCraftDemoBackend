Feature: UserController API tests

  Background:
    * url 'http://localhost:8080/api/user'

  @Test
  Scenario: Valid User
    Given path '/validUser'
    And request { email: 'test@example.com', password: 'password' }
    When method post
    Then status 200
    And match response == 'User verified successfully.'

  @Test
  Scenario: Invalid User
    Given path '/validUser'
    And request { email: 'invalid@example.com', password: 'password' }
    When method post
    Then status 404
    And match response == 'User not found or invalid credentials.'

  @Test
  Scenario: Get User
    Given path '/get'
    And request { email: 'test@example.com' }
    When method post
    Then status 200
    And match response.email == 'test@example.com'

  @Test
  Scenario: Get User Not Found
    Given path '/get'
    And request { email: 'notfound@example.com' }
    When method post
    Then status 404

  @Test
  Scenario: Signup New User
    Given path '/signup'
    And request { email: 'newuser@example.com', password: 'newpassword', firstName: 'New', lastName: 'User' }
    When method post
    Then status 200
    And match response.email == 'newuser@example.com'

  @Test
  Scenario: Update User
    Given path '/update'
    And request { email: 'test@example.com', password: 'newpassword' }
    When method put
    Then status 200
    And match response.password == 'newpassword'

  @Test
  Scenario: Sign In
    Given path '/signin'
    And request { email: 'test@example.com', password: 'newpassword' }
    When method post
    Then status 200
    And match response == 'User verified successfully.'

  @Test
  Scenario: Sign In Invalid User
    Given path '/signin'
    And request { email: 'test@example.com', password: 'wrongpassword' }
    When method post
    Then status 404
    And match response == 'User not found or invalid credentials.'

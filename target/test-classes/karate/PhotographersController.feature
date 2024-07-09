Feature: Testing PhotographersController API endpoints

  Background:
    * url 'http://localhost:8080/api'

  Scenario: Get all photographers
    Given path '/photographers'
    When method get
    Then status 200
    And match response == $response

  Scenario: Get photographer by ID
    Given path '/photographers/{photographerID}'
    And path photographerID
    When method get
    Then status 200
    And match response == $response

  Scenario: Get photographers by event type
    Given path '/photographers/event/{eventType}'
    And path eventType
    When method get
    Then status 200
    And match response == $response
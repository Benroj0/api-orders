Feature: orders API
  Scenario: Create a new order via REST
    Given the orders API is up
    When I send a POST request to "/api/orders" with name "Benja" and price 50.0
    Then the response status should be 201

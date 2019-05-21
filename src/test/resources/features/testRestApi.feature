Feature: Test Rest Api

  @testApi @all
  Scenario:
    When Get guest token
    When Registration player and check response
    When Authorization player
    When Get created profile player and check response
    When Get another profile player
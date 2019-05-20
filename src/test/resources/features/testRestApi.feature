Feature: Test Rest Api

  @testApi
  Scenario:
    Given Get guest token
    Given Registration player and check response
    Given Authorization player
    Given Get created profile player and check response
    Given Get another profile player
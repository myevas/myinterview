@test
Feature: Sort by any column
  Background:
    Given Open login page "http://test-app.d6.dev.devcaz.com/admin/login"
    And User fill field with the parameters "Login" "admin1"
    And User fill field with the parameters "Password" "[9k<k8^z!+$$GkuP"
    And user press button "submit"

  @test
  Scenario: Sort by name column
    Given Check open main page
    And Open players list
    And Check open displayed table
    And Sort by name
    Then Check result
Feature: add and navigate stones

  Scenario: add stone
    When id click add button
    Then a dialog appear, with choose wish, input content
    Then  i click ok
    Then  save the stone
    Then stone list refresh

  Scenario: navigate stones
    When to main page list the stones belong to current wish


  Scenario: change wish
    When click choose , show a list of wishes list
    When chosen
    Then highlight the current wish
    Then refresh stone list to current wish

  Scenario: add activity
    Given in the stone list,each item given a add activity button
    When click add activity button
    Then  dialog add activity show
    Then input activity type
    Then input activity content
    Then click ok
    Then an activity is added to stone
    Then stone's activity list refreshed

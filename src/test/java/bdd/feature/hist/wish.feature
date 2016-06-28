Feature: fire wish and navigate and fire pray

  Scenario: fire wish
    When has a belong to,then show belong to
    When no belong to, then given a link to choose belong
    Then input wish content
    Then click ok
    Then wish fired
    Then the wish list refresh

  Scenario: fire pray
    Given wish list
    Then in the wish item , given a button 'pray'
    When click pray
    Then a pray is added
    Then wish refresh with   pray listed

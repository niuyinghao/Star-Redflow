Feature:  wave   add  and navigate  , fire wish in a flow

  Background: login

  Scenario:  add
    Given click the button add
    And  i input the content
    Then a wave is generated, with a unique sign generated
    Then wave list show the added flow latest added

  Scenario:     navigate
    When click the previous button
    Then list the previous wave
    When click the next button
    Then list the next wave

  Scenario: fire wish
    When click 'add wish ' button within wave
    Then open wish window
    When wish fired
    Then list wished in it's wave



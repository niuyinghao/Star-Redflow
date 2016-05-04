Feature:  flower   add  and navigate  , fire wish in a flow

  Background: login

  Scenario:  add
    Given click the button add flower
    And  i input the content
    Then a flower is generated, with a unique sing generated
    Then flower list show the added flow latest added

  Scenario:     navigate
    When click the previous button
    Then list the previous flower
    When click the next button
    Then list the next flower

  Scenario: fire wish
    When click 'add wish ' button within flower
    Then open wish window
    When wish fired
    Then list wished in it's flower

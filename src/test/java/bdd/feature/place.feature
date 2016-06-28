Feature:

  Background:
    Given user has login with "<username>" and "<password>"
      | STAR-00F0 | welcome |

  Scenario: heart age
    Given  in the sea page
    When  i click heart
    Then the hear enter into age change


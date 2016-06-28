#language:en
Feature: system misc

  @test
  Scenario Template:  register user
    Given user in register page:
    And usename is auto generated
    """
       generated format like STAR-0001
    """
  """
       see if has any second param
    """
    When input "<passwrod>" , "<confirmPasswd>" , "<email>"; with "<id>"
    Then the account register, user got login
    Examples:
      | id | passwrod | confirmPasswd | email           |
      | 1  | welcome  | welcome       | email           |
      | 2  | welcome  | welcome       | email@email.com |
      | 3  | welcome  | welcome       |                 |
      | 4  | welcome  |               |                 |
      | 5  |          | welcome       |                 |
      | 6  | welcome2 | welcome       |                 |

Feature: publish a say, and see comment, and comment others

  Scenario: publish a say
    When input content
    And  click add
    Then say published
    Then say list refresh

  Scenario: delete and edit a say
    Given a delete and edit button at the end of the item of the say list
    When click delete
    Then the say deleted
    When click edit
    Then the say could be edit

  Scenario: comment others
    Given :there is a 'view others' in the tools bar
    When  click 'view others  '
    Then published comment could be viewed and searched
    Given the say of others there two button like and dislike at the end
    When click like then appear comment
    Then click ok
    Then add comment success
    When click dislike then appear comment
    Then click ok
    Then add dislike comment success

Feature: navigate publish article and collection feedback

  Scenario: publish article
    Given in main page
    Then navigate to number of articles published
    Given user has red article, the red article show pop in the position queue


  Scenario: feedback
    Given a feedback button on the tool bar
    When click the feedback button , then show a dialog
    Then user input feedback
    Then send the feedback
    Then refresh feedback history content

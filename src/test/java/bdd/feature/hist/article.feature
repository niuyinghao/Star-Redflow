Feature: navigate publish article and collection feedback

  @smoke
  Scenario: add article
    Given  具有管理员权限
    Then 进入添加文章界面
    When 点击添加文章按钮
    Then 跳转到添加文章界面
    When 录入文章内容
    And 点击提交按钮
    Then 添加了一个新文章

  Scenario: publish article
    Given in main page
    Then navigate to number of articles published
    Given user has red article, the red article show pop in the position queue

  @feedback
  Scenario: feedback
    Given a feedback button on the tool bar
    When click the feedback button , then show a dialog
    Then user input feedback
    Then send the feedback
    Then refresh feedback history content

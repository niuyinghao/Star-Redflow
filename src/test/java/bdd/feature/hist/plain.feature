Feature: navigate and bury mounds

  Scenario: bury mounds
    When click a new mound
    Then a dialog show
    Then one can choose wave , flower , wish , stone to be buried
    Then click ok , chosen is associate to the mound
    Then choose tool bury
    Then click on the mound
    Then each click will increase the depth of bury , and mound become dark
    Then each target associated with bury is become also dark
    When it become the most dark
    Then associated become disappear
    When choose tool un bury
    Then click on mounds
    Then the mound depth will decrease , and become lighter
    Then  the association will become lighter also

  Scenario: un associate mound
    Given associated mound , each target given a cancel button,
    When click cancel
    Then it become associated

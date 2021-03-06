Feature:   add wave content
And reflect wave heartSymbol age change


  Background:
    Given user has login with "<string>" and "<string>"
#       | STAR-00F0 | welcome |
#      | STAR-0176 | welcome |
#      | STAR-0174 | welcome |
#      | STAR-0178 | welcome |
#      | STAR-017A | welcome |
      | STAR-0180 | welcome |
      | STAR-0182 | welcome |

  @wave  @test
  Scenario: add wave
    Given in sea page and add a wave
    Then even first add , should see next link


  Scenario Template: HeartSymbol Aging
    Given there is a wave
    When click the wave's hearSymbol
    Then the heart become aging max 3 * 4= 12
    And the heart is change in style with age
     """
          0 the initial state
          1 - 4 mature age   opacity change , from transparent to solid.
          5 - 8  grow age , from small to bigger;
          9 - 12  dying age, more and more older to die
     """
    Examples:
      |  |
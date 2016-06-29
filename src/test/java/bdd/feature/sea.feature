 Feature:   add wave content
    And reflect wave heartSymbol age change


   Background:
     Given user has login with "<string>" and "<string>"
       | STAR-00F0 | welcome |

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
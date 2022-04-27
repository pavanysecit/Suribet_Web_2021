Feature: Test all the functionalities of Pai Gow Poker module in Suribet Website on Web version

		Scenario: Screenshots for every win trigger in Pai Gow Poker website
    Given  Web: Chrome browser, pai gow poker module, win combinations and screenshots
    When Web: wait for result part to be active
    Then Web: take screenshots after each win for win combination matching in pai gow poker website	

Feature: Test all the functionalities of Virtual Baccarat module in Suribet Website on Web version		
		
		Scenario: Screenshots for every win trigger in Virtual Baccarat website
    Given  Web: Chrome browser, Virtual Baccarat module, win combinations and screenshots
    When Web: wait for result part to be active in Virtual Baccarat website
    Then Web: take screenshots after each win for win combination matching in Virtual Baccarat website	
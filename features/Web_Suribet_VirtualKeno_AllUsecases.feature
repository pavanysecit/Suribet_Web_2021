Feature: Functional Testing of Virtual Keno Website 

# Class Name: Web_VirtualKeno_OnlineLogin_CardLogin_Validations
#	Scenario: Checking the Login Functionalities for Virtual Keno
#	Given Email Login: Launch the chrome browser, Enter valid Url, Enter Valid & InValid login credentials and verify the validation
#	When Card Login: Launch the chrome browser, Enter valid Url, Enter Valid & InValid card login credentials and verify the validation
#	Then User should be login successfully with valid Email and password credential
#	And User should NOT be login successfully with Invalid Email and password credentials
#	Then ser should be login successfully with valid CardNumber and Pin Number
#	And User should NOT be login by entering invalid card details and display Invalid validation message 
#	And Close the virtual Keno Application successfully

# Class Name: Web_VirtualKeno_Online_PalceBet_CanceBet_VerifyBalance	
#	Scenario: Verify Functionality of PlaceBet, CancelBet, Balance Debit & Credit for Online login
#	Given Login: Launch chrome browser, Enter valid Url, Enter valid credential, PlaceBet, cancel Bet and verify balance update
#	When PlaceBet: Select the DrawID, Choose the Betting Numbers, Confirm the betting numbers, Enter stake and Confirm bet, Verify Print slip and cancel slip buttons
#	Then Cancel Bet: click on Cancel Slip button and cancel the bet successfully
#	And Verify the Balance After bet has been cancelled
#	And Close the browser successfully
	
# Class Name: Web_VirtualKeno_CardLogin_PalceBet_CanceBet_VerifyBalance
#	Scenario: Verify Functionality of PlaceBet, CancelBet, Balance Debit & Credit for Card login
#	Given Card Login: Launch chrome browser, Enter valid Url, Enter valid credential, PlaceBet, cancel Bet and verify balance update
#	When PlaceBet: Select the DrawID, Choose the Betting Numbers, Confirm the betting numbers, Enter stake and Confirm bet, Verify PrintSlip and cancel  buttons
#	Then Cancel Bet: click on CancelSlip button and cancel the bet successfully
#	And Verify the Cancelled Balance is credited to main Balance
#	And Close a Browser Successfully
	
# Class Name: Web_VirtualKeno_PayByCard_PalceBet_CanceBet_VerifyBalance
#	Scenario: PayByCard: Verify Functionality of PlaceBet, CancelBet using PayByCard
#	Given Login: Launch chrome browser and Enter valid Url
#	When PlaceBet: Select the DrawID, Choose the Betting Numbers, Confirm the betting numbers, Enter valid stake 
#	Then Then Select the PayByCard payment option and enter the card details proceed and Confirm bet
#	And Cancel Bet: click on Cancel Slip button and enter the valid card details to cancel the bet successfully

# Class Name: Web_VirtualKeno_MultipleDraws_PlaceBet_CancelBet_BalanceValidations 
#	Scenario: Multiple Draw: Verify the functionality of Multiple Draw bets
#	Given Launch chrome browser, Enter Keno valid Url and Card Login
#	When PlaceBetforMultipleDraw, CancelAll BetForMultipleDraw, Balance validation Before & After cancel Multiple Draw Bet
#	Then PlaceBetforMultipleDraw, Cancel Individual Bet and verify the balance validation
#	And Logout and close the browser

# Class Name: Web_VirtualKeno_ReplayForSinglebet_ReplayforMultipleDraw	
#	Scenario: Replay Scenarios: Verify Single and MultipleDraw Replay functionalities 
#	Given launch the chrome browser, Enter the website url and login to online account
#	When Place the single bet, Place one more bet to Cancel bet, then click on replay button and verify replay valid bet details
#	Then PlaceBetforMultipleDraw and verify the Replay button.
#	And Logout and quite the browser

# Class Name: Web_VirtualKeno_SwitchToCard_PlaceBet_CancelBet_SwithToOnline_PlaceBetCanceBet	
#	Scenario: Switch Payments: SwitchToCard, Place the bet and cancel bet, Then SwitchToOnline Place bet and cancel bet
#	Given Open the chrome browser, Enter the website url and login to online account first
#	When SwitchToCard payment option, Place the bet and Cancel the bet
#	Then SwitchToOnline Payment option, Place bet and cancel bet
#	And logout and close the browser window

# Class Name: Web_VirtualKeno_MinMaxBet_ClearBet_Undo_QuickPicks_NextDrawNumTime_SearchSlip_LastWin_Timelapse	
#	Scenario: Verify MinMaxBet, ClearBet, Undo, QuickPicks, NextDrawNumberTime, Search slip, Last win, Time lapse, Game info
#	Given Open the chrome browser, Enter the valid website url and login to online account
#	When Verify MinMaxBet, ClearBet, Undo, QuickPicks, NextDrawNumberTime
#	Then verify Search slip, Last win, online login cancel bet Time lapse, Game info
#	And logout and close the opened browser

# Class Name: Web_VirtualKeno_BetTableMultiplier_SlipDetails_SearchResult_AccountSummary	
#	Scenario: Verify BetTableMultipliers, Verify SlipDetails, SearchDrawResult and AccountSummary
#	Given launch the chrome browserr, Enter the valid url and login to online account
#	When Verify BetTableMultiplier, SlipDetails, SearchDrawResult
#	Then verify the AccountSummary page details
#	And Close the Browser

# Class Name: Web_VirtualKeno_Verify_All_validation_Messages
#	Scenario: Verify All validation Messages
#	Given Open the chrome browser and Enter the valid website url link and login to online account
#	When verify Result Processed! Slip can not be cancelled!
#	Then Dublicate Bet Not Allowed
#	And You have not selected any bet
#	Then The Selected Bet Number & Number of rows in cart
#	And Bet Not Allowed For the DrawID
#	Then Without Login Replay validation Please Do Login
#	And You must login before place a bet
#	Then Bet number should not add more than Ten numbers
#	And Current Draw and Time verified
	
# Class Name: Web_VirtualKeno_Live_and_RecentResult_Matching	
# Scenario: Live Result matching with Recent Result.
#	Given Open the Chrome browser and Enter the valid url
#	When Enter the valid account id and password and login to online account
#	Then Compare the live result winning numbers with Recent result winning numbers
#	And Close the Keno browser application

Feature: Test all the functionalities of Skinfiri module in Suribet Website on Web version

	#Web_Skinfiri_InvalidLogin_Validations
	
		Scenario: Verify the invalid login validation messages under all login modes 
		Given  Web: Chrome browser, suribet website valid URL, sports betting module, invalid login details
		When Web: Try to login with invalid different combinations under all modes of login methods
		Then Web: Validate the account shouldn't login and valid user message is displayed to user under different login modes
	
	
	#Web_Skinfiri_OnlineLogin_BarCodeField_GameInfo_Validations 

	#Scenario: Verify functionalities of Skinfiri module like barcode and slip id details
    #Given  Web: Chrome browser, suribet website valid URL, sports betting module,login via online method, place bet, get the slip ID, valid and invalid input 
    #When Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fields in the slip generator
    #Then Web: Verify the field with valid slip id while the bets are not placed for specific slip id by the user and verify the result
    #Then Web: Verify the field with invalid slip id and verify the validation message is displayed to user
	#Then Web: Verify the search slip after logout and search for valid and invalid inputs
	
	
	#Web_Skinfiri_AccountSummaryPage_Validations 
	
	#Scenario: Verify the Account Summary page like paginations, number of rows selections, filter with dates and modules, filetring bet details
    #Given  Web: Chrome browser, suribet website valid URL, sports betting module, valid logins, Account summary page 
    #When Web: Navigate to account summary page and modify the search pattern like date module row length 
	#Then Web: Result has to be displayed to the user as per the search combinations 
	
	
	#Web_Skinfiri_PlaceBet_CancelBet_BalanceRefresh_Validations 
	
	#Scenario: Verify basic functionalities of Skinfiri module like Place bet can cancel the bet with the balance being updated for every bets
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, Roulette table, stake amount, place bet, balance, cancel bet and print slip
    #When Web: Login to suribet website with valid login details, Click on Skinfiri module link, place bet with selecting the stake amount
    #Then Web:  Balance has to dedcuted respective with bet placed, and print slip has to be geneated
	#And Web: Cancel the bet placed and check with the same amount being updated to the main balance
	#And Web: Validation successful message for placing bet should be generated
	#And Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance
	#And Web: Logout and place the bet and check whether bets can be placed after logout from the suribet and verify the validation message


	 #Web_Skinfiri_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation

	#Scenario: Verify functionalities of Skinfiri module like login via gmail and switch to card, place bet, remove certain placed bet and later cancel via account details page
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, Roulette table, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet
    #When Web: Login to suribet website with valid login details, Click on Skinfiri module link, change to card mode of payment
    #Then Web:  Place as many as bets and check the bet rows before and after selecting the remove options
	#And Web: Forward and confirm the bets and validate the user message and direct to account details page
	#And Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Skinfiri module
	

 
	#Web_Skinfiri_ClearBets_IncreaseOrDecrease_Stake_Validations
	
    #Scenario: Verify basic functionalities of Skinfiri module like clear All, clear individual, increase or decrease stake amount individually, 	
    #Given  Web: Chrome browser, suribet website valid URL,  Skinfiri module, place bet, Clear All, clear individually, stake increase or decrease individually
    #When Web: Login to suribet website with valid login details, redirect to Skinfiri module link and place bet 
    #Then Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively in Skinfiri module
	 
	 
	 #Web_Skinfiri_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations
	
	#Scenario: Verify functionalities of Skinfiri module like timelapse cancel bet balance updation in card login mode of transactions
    #Given  Web: Chrome browser, suribet website valid URL, Skinfiri module, place bet, time period, balance
    #When Web: Place bet and cancel bet after 2min and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance
    #Then Web: Validate balance refresh functionality and bet status with time period for cancellation of bets via card login


	#Web_Skinfiri_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations
	
    #Scenario: Verify functionalities of Skinfiri module like pay by card functionality
    #Given  Web: Chrome browser, suribet website valid URL, skinfiri module, place bet and pay the amount via card 'PAY BY CARD' option
    #When Web: Login to suribet website with valid login details, Click on Skinfiri module link and place bet 
    #Then Web: Select 'PAY BY CARD' option and fill the card details and accept the pop-up
	#And Web: Cancel the bet by click on the Cancel Slip button and fill the card details 
	#And Web: Successfull cancelling bet user message is displayed to user in Virtual sports betting module
	#And Web: Again place bet and wait for 2mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button
	
	
	#Web_Skinfiri_Login_Home_Validations
   
    #Scenario: Load the suribet website with valid URL, verify login scenarios and home button functionalities
    #Given  Web: Chrome browser, suribet website valid URL, valid & invalid login details and home button
    #When Web: Open the chrome browser, Enter the valid suribet URL, Click on Skinfiri module link, enter valid and invalid login details and click on home button
    #Then Web: User should be blocked from logging to the suribet website
	#And Web: User should able to see the Skinfiri module link in the home page and should see Skinfiri module home page after login & clicking on Skinfiri module
	#And Web: User should be navigated to the Home page of suribet website after clicking on home link
	
	 
	 #Web_Skinfiri_etTableDetails_BetSlipCrossVerification_Language_Validations
	
	#Scenario: Verify the bet details table above the roulette table, cross verify the bet selected in bet selection table is same displayed on betting slip and the language change 
    #Given  Web: Chrome browser, suribet website valid URL, skinfiri module, valid logins, bet details table, bet place   
    #When Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details
    #Then Web: Verify the bet details table before place bet
    #Then Web: Cross verification of betting slip with the skinfiri table bet selection  
	#Then Web: Verify the language change reflects on the web page 
	
	
	
	#Web_Skinfiri_Bets_Selection_5pick_10pick_Replay_Next10Draws
	
	#Scenario: Verify the functionality of Bets_Selection Quick pick, 05 pick, 10 pick 
	#Given Web: Chrome browser, suribet website valid URL, Skinfiri module, valid login,
	#When Web: Login to Skinfiri, select and click on Quickpick, 05 pick, 10 pick buttons
	#Then Web: Five rows as five betting numbers should be displayed under betting slip
	#And Web: Ten rows as ten betting numbers should be displayed under betting slip
	#And Web: Previously placed betting numbers should get displayed in the betting slip on click of Replay button 
    #And Web: Selected betting number should get selected for next  upcoming draws for the selected day
	
	
	
	#Web_Skinfiri_OnlineLogin_HideAndUnhide_BettingSlip_GameSection_AccountOpenAndCloseBalance_Validations
	
	#Scenario: Verify functionalities of Skinfiri module like hide and unhide the BettingSlip and game sections and the open and close balance after login and logout and re-login with successfull and cancelling bets combinations validations
	#Given  Web: Chrome browser, suribet website valid URL, skinfiri module,login via online method, betslip arrow, game section arrows, login balance and logout balance
	#When Web: note the login balance, and click on the hide and un hide buttons located on all game section and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user
	#Then Web: Validate the hide and un-hide functionality under game section and betting slip sections and the balance validations after relogin to the suribet account
	
	
		
	
	
	#Web_Skinfiri_MulripleDraws_PlaceBet_CancelBet_Validations 
	
	#Scenario: Verify functionalities of Skinfiri module like multiple draws and cancelling the single and entire multi draws
	#Given  Web: Chrome browser, suribet website valid URL, skinfiri module,login via online method, upcoming matches table place for multiple draws, cancel slip
	#When Web: Login to skinfiri and place bet for multiple draws and try cancelling each slips and multiple cancelling of slips 
	#Then Web: Validate the multiple draws is available to user and user is able to cancel the individual placed bet from multiple cancel slip sheet
	#Then Web: Validate the multiple draws can be cancelling in a single click on cancel slip button
	
	
		
	
	#Web_Skinfiri_SessionExpiry_Resultlist_Validations
	
	#Scenario: Verify functionalities of Skinfiri module SessionExpiry timeduration, gamelist and result list validations
    #Given  Web: Chrome browser, suribet website valid URL, skinfiri module,login via online method, Session Expiry timeduration and result list search validations
    #When Web: Login to skinfiri, gamelist and result list button validations, and place bet and wait for the session expiry and try placing bet  and verify the result search validations
    #Then Web: Navigate to the result page and verify the result based on different criterias
	#And Web: Web: Validate the valid user message displayed to user and the button validations 
	
	
	

	
Feature: Test all the functionalities of Live Spin2Win module in Suribet Website on Web version


#Web_LiveSpin2Win_SessionExpiry Validations
	
#Scenario: Verify basic functionalities of live Spin2Win module like session expiry time duration validations
#Given  Web: Chrome browser, suribet website valid URL, idle time duration
#When Web: Login to suribet website with valid login details, Click on live Spin2Win module link, be idle for about 20 minutes 
#Then Web: Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session 
	
	
#Web_LiveSpin2Win_AccountSummaryPage_Validations
     
#Scenario: Verify the Account Summary page like paginations, number of rows selections, filter with dates and modules, filetring bet details in live Spin2Win module
#Given  Web: Chrome browser, suribet website valid URL, live Spin2Win module, valid logins, Account summary page 
#When Web: Navigate to account summary page and modify the search pattern like date module row length and filter date selections
#Then Web: Result has to be displayed as per the search combinations in live Spin2Win module
	
	

#Web_LiveSpin2Win_StatitcsData_Validations
	
#Scenario: Verify the Statitcs like count increasing by the win combinations and all the win combination values are displayed to user and not null 
#Given  Web: Chrome browser, suribet website valid URL, live Spin2Win module, valid logins, statitcs table, count time
#When Web: Fetch the staticts table details before the time lapse, after the successfull bet, validate the statitcs results
#Then Web: Validate the win combination count is updated in the statitcs table after each round   
	
	
#Web_LiveSpin2Win_PlaceBet_CancelBet_BalanceRefresh_Validations

#Scenario: Verify basic functionalities of LiveSpin2Win module like Place bet can cancel the bet with the balance being updated for every bets
#Given  Web: Chrome browser, suribet website valid URL, Draw details, LiveSpin2Win table, stake amount, place bet, balance, cancel bet and print slip
#When Web: Login to suribet website with valid login details, Click on LiveSpin2Win module link, place bet with selecting the stake amount
#Then Web:  Balance has to deducted respective with bet placed
#And Web: Cancel the bet placed and check with the same amount being updated to the main balance



#Web_LiveSpin2Win_Login_Home_Validations
   
#Scenario: Load the suribet website with valid URL, verify login scenarios and home button functionalities
#Given  Web: Chrome browser, suribet website valid URL, valid & invalid login details and home button
#When Web: Open the chrome browser, Enter the valid suribet URL, Click on live Spin2Win module link, enter valid and invalid login details and click on home button
#Then Web: User should be blocked from logging to the suribet website
#And Web: User should able to see the live Spin2Win module link in the home page and should see live Spin2Win module home page after login & clicking on live Spin2Win module
#And Web: User should be navigated to the Home page of suribet website after clicking on home link
	
	
	
#Web_LiveSpin2Win_clearAll_2X_Clear_ReplayButtons_Validations
	
#Scenario: Verify functionalities of live Spin2Win module like replay, clear and 2X button validations
#Given  Web: Chrome browser, suribet website valid URL, live Spin2Win module, place bet, replay, clear all and clear validations messages
#When Web: place bet and clear some bets and clear all sections and with 2X multiplier on the same bet with replay validations
#Then Web: Validate the all the functionalities are working as expected
	
	

#Web_LiveSpin2Win_Search_DrawResult_Validations
	
#Scenario: Verify functionalities of live Spin2Win module like draw result tab under various inputs
#Given  Web: Chrome browser, suribet website valid URL, live Spin2Win module, click on search draw tab and verify the previous draw results are displayed to the user
#When Web: Change the dates and verify under search is selected, all draw results are displayed to user in draw result tabs with and without win combinations
#Then Web: Search with blank draw num and verify the pervious draw is displayed, with bet number and bet color 
#And Web: Search with invalid draw numbers and verify the validation results for '0' and above '900' invalid draw results 
	 
	
	
	
#Web_LiveSpin2Win_BetTableDetails_BetSlipCrossVerification_Language_Validations
	
#Scenario: Verify the bet details table above the spin table, cross verify the bet selected in live spin table is same displayed on betting slip and the language change 
#Given  Web: Chrome browser, suribet website valid URL, live Spin2Win module, valid logins, bet details table, bet place and bet won and updated bet tables  
#When Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details
#Then Web: Verify the bet details table before place bet, after bet placed and once after current bet is completed 
#Then Web: Cross verification of betting slip with the live spin table bet selection  
#Then Web: Verify the language change reflects on the web page 
	
	
	
	
#Web_LiveSpin2Win_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation

#Scenario: Verify functionalities of LiveSpin2Win module like login via gmail and switch to card, place bet, later cancel via account details page
#Given  Web: Chrome browser, suribet website valid URL, Draw details, spin2win table, stake amount, place bet, balance,account details page, cancel bet alert, cancel the bet
#When Web: Login to suribet website with valid login details, Click on LiveSpin2Win module link, change to card mode of payment
#And Web: Forward and confirm the bets and validate the user message and direct to account details page
#Then Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in LiveSpin2Win module

	

#Web_LiveSpin2Win_ChipDenominations_Addition_Validations
	
#Scenario: Verify functionalities of LiveSpin2Win module like chip denominations, chip bets lock state on roulette with chip denomination addition on the bet table
#Given  Web: Chrome browser, suribet website valid URL, LiveSpin2Win module, place bet, chip denominations, lock state of chip denominations, bet table
#When Web: Login to suribet website with valid login details, select VirtualRoulette module link 
#Then Web: Select some numbers on roulette table and add the same table num with more chip denominations and place bet 
#And Web: After placed bet check for the chips are still in lock condition for selected bets  
 
	
	
#Web_LiveSpin2Win_OnlineLogin_BarCodeField_GameInfo_Statistics_Validations

#Scenario: Verify functionalities of LiveSpin2Win module like barcode, slip id details, gameinfo and statistics
#Given  Web: Chrome browser, suribet website valid URL, LiveSpin2Win module,login via online method, get the slip ID, valid and invalid input gameinfo button and statistics
#When Web: Get the slip id and search for the validation of the slip id 
#Then Web: verify the how to play link is active 
#Then Web: Verify the statistics window is displayed to user

	

#LiveSpin2Win_Web_WithoutLoginPlaceBet_Validations
	
#Scenario: Verify functionalities of LiveSpin2Win module like place bet under logout condition
#Given  Web: Chrome browser suribet website valid URL roulette module place bet replay validations messages
#When Web: Direct to the module and place bet in logout condition with and without bets and try to rebet under logout conditions
#Then Web: Validate the appropriate user message displayed bet placing under logout conditions
	
	
	
#Web_LiveSpin2Win_ChangeTable_BetPlace_Validations
	
#Scenario: Verify functionalities of LiveSpin2Win module like changing the bet table and placing bet
#Given  Web: Chrome browser suribet website valid URL LiveSpin2Win module tropicana bet table and carnival table
#When Web: login to tropicana table and change table to carnival and place bet and again redirect to tropicana table
#Then Web: Validate the appropriate table change is reflected to user and able to place bet 
	
	
#Web_LiveSpin2Win_BetCombination_Validations
	
#Scenario: Verify functionalities of LiveSpin2Win module like all the bet combinations available for the user to place the bets 
#Given  Web: Chrome browser suribet website valid URL LiveSpin2Win module tropicana bet table red split, black split, complete bet and neighbourings
#When Web: login to tropicana table place bets on all available combination bets available to the user 
#Then Web: Validate the appropriate combinations are selected on the betting tray for the user to place bets
	
	
	
Feature: Test all the functionalities of Poker module in Suribet Website on Web version		
		
	#Scenario: Screenshots for every win trigger
    #Given  Web: Chrome browser, poker module, win combinations and screenshots
    #When Web: wait for river block to be active
    #Then Web: take screenshots after each win for win combination matching
    
     # VirtualPoker_Web_PlaceBet_CancelBet_BalanceRefresh_Validations

	#Scenario: Verify basic functionalities of VirtualPoker module like Place bet can cancel the bet with the balance being updated for every bets
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, hands, stake amount, place bet, balance, cancel bet and print slip
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link, place bet with selecting the stake amount by selecting the hands
    #Then Web:  Balance has to decuted respective with bet placed, and print slip has to be geneated
	#And Web: Cancel the bet placed and check with the same amount being updated to the main balance
	#And Web: Validation successful message for placing bet should be generated
	#And Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance


    #VirtualPoker_Web_Search_DrawResult_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module like draw result tab under various inputs
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, click on search draw tab and verify the previous draw results are displayed to the user
    #When Web: Change the dates and verify under search is selected, all draw results are displayed to user in draw result tabs with and without win combinations
    #Then Web: Search with blank draw num and verify the pervious draw is displayed, with hand number and win type
	#And Web: Search with invalid draw numbers and verify the validation results for '0' and above '479' invalid draw results 
	
	
	
	#VirtualPoker_Web_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation (Verify)

	#Scenario: Verify functionalities of VirtualPoker module like login via gmail and switch to card, place bet, remove certain placed bet and later cancel via account details page
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, hands and win pattern, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link, change to card mode of payment
    #Then Web:  Place as many as bets and check the bet rows before and after selecting the remove options
	#And Web: Forward and confirm the bets and validate the user message and direct to account details page
	#And Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Virtual Poker module
	
	 
	 
	#VirtualPoker_Web_AccountSummaryPage_Validations
	
	#Scenario: Verify the Account Summary page like paginations, number of rows selections, filter with dates and modules, filetring bet details in Poker module
    #Given  Web: Chrome browser, suribet website valid URL, Poker module, valid logins, Account summary page 
    #When Web: Navigate to account summary page and modify the search pattern like date module row length and filter date selections
	#Then Web: Result has to be displayed as per the search combinations in Poker module
	
	 
	
	#VirtualPoker_Web_BetTableDetails_BetSlipCrossVerification_Language_Validations (Scripting)
	
	#Scenario: Verify the bet details table above the poker table, cross verify the bet selected in hands or combination selection window is same displayed on betting slip and the language change 
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, valid logins, bet details table, bet place and bet won and updated bet tables  
    #When Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details
    #Then Web: Verify the bet details table before place bet, after bet placed and once after current bet is completed 
    #Then Web: Cross verification of betting slip with the roulette table bet selection  
	#Then Web: Verify the language change reflects on the web page 

	 
	 
	#VirtualPoker_Web_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations (Scripting)
	
	#Scenario: Verify functionalities of VirtualPoker module like timelapse cancel bet balance updation in card login mode of transactions
    #Given  Web: Chrome browser, suribet website valid URL, virtual VirtualPoker module, place bet, time period, balance
    #When Web: Place bet and cancel bet after 1min and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance
    #Then Web: Validate balance refresh functionality and bet status with time period for cancelation of bets via card mode login
	
	 
	 
	#VirtualPoker_Web_ChipDenominations_Addition_RefreshPage_Validations  (Have to verify)
	
	#Scenario: Verify functionalities of VirtualPoker module like chip denominations, chip bets lock and unlock state on hands and win pattern table numbers under refreshpage, navigation to other sites and after re-login to virtual poker module having previously selected draw num
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, place bet, chip denominations, lock and unlock state of chip denominations, refresh page, login and logout
    #When Web: Login to suribet website with valid login details, select VirtualPoker module link 
    #Then Web: Select some hands on bet option table and add the same table num with more chip denominations and place bet 
	#And Web: After placed bet check for the chips are still in lock condition for selected bets later navigate to other tabs and refresh virtual poker game page, logout and again login with same credentails 
	#And Web: Redirect to same draw num and verify whether previously placed bet are still in lock with selected numbers on poker table 
	
	 	 
	 
	#VirtualPoker_Web_InvalidLogin_Validations
	
	#Scenario: Verify the invalid login validation messages under all login modes 
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, invalid login details
    #When Web: Try to login with invalid different combinations under all modes of login methods
    #Then Web: Validate the account shouldn't login to poker module and valid user message is displayed to user under different login modes



    #VirtualPoker_Web_Login_Home_Validations
   
    #Scenario: Load the suribet website with valid URL, verify login scenarios and home button functionalities
    #Given  Web: Chrome browser, suribet website valid URL, valid & invalid login details and home button
    #When Web: Open the chrome browser, Enter the valid suribet URL, Click on Vitual Roulette module link, enter valid and invalid login details and click on home button
    #Then Web: User should be blocked from logging to the suribet website
	#And Web: User should able to see the virtual poker module link in the home page and should see virtualpoker module home page after login & clicking on virtualpoker module
	#And Web: User should be navigated to the Home page of suribet website after clicking on home link



	#VirtualPoker_Web_MultipleDraws_PlaceBet_CancelBet_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module like Multiple draws place bet and cancel bet individually and check the balance after cancelling
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, place bet selecting multiple draws 
    #When Web: Cancel slip according to draw numbers individually and validate the balance and cancel slip remaining draws
    #Then Web: Validate balance refresh functionality and added to main balance after cancelling the slips
	
	
	
	#VirtualPoker_Web_BettingSlip_BettingTray_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module like betting tray details bet placed values are displayed over betting slip
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, place bet, betting slip and betting tray
    #When Web: place bet and fetch the selected bets details from betting tray and from the betting slip details
    #Then Web: Validate the details from the collected datas and validate any mis matching from the selected bets from the displayed bets
	
	
	
	#VirtualPoker_Web_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations
	
    #Scenario: Verify functionalities of VirtualPoker module like pay by card functionality
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, place bet and pay the amount via card'PAY BY CARD' option
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link and place bet 
    #Then Web: Select 'PAY BY CARD' option and fill the card details and accept the pop-up
	#And Web: Cancel the bet by click on the Cancel Slip button and fill the card details 
	#And Web: Successfull cancelling bet user message is displayed to user in virtual poker module
	#And Web: Again place bet and wait for 2mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button
	
	
	
	
	#VirtualPoker_Web_Draw_BetNumbers_BetAmount_Verifications
	
	#Scenario: Verify basic functionalities of VirtualPoker module like verifying current draw, future draw, bet numbers, place a bet without login, verify minimum bet, maximum bet chip value 
    #Given  Web: Chrome browser, suribet website valid URL, current draw, bet numbers, mimimum bet, maximum bet
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link, cross check current draw , bet numbers and validation message for mimimum bet & maximum bet 
    #Then Web:  System should show Current Draw#, Date, Time and for the future draw in the VirtualPoker module
	#And Web: Selected Draw No#, Time on the VirtualPoker module screen
	#And Web: Validation message for placing bet without login to the website
	#And Web: System should not allow user to enter the stake amount in whole numbers, It should be auto calculated in VirtualPoker module
	
	
	
	#VirtualPoker_Web_SessionExpiry_Validations
	
	#Scenario: Verify basic functionalities of VirtualPoker module like session expiry time duration validations
    #Given  Web: Chrome browser, suribet website valid URL, idel time duration
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link, be ideal for about 20 minutes 
    #Then Web:  Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session 

	
	 #VirtualPoker_Web_MaxWinLimit_Last2Result_Validations
	  
	
	#Scenario: Verify basic functionalities of VirtualPoker module like max win limit allowed to win in a single bet and last two draw results
    #Given  Web: Chrome browser, suribet website valid URL, bet placing, all bet place combinations, user message and the last two draw results
    #When Web: Login to suribet website with valid login details, Click on VirtualPoker module link, placed bet exceeding win amount more than 5000  
    #Then Web:  Validate the bet placing is not allowed where win amount exceeds '5000' and verify the last draw results are displayed to user
	  
	  
	  	  
	  #VirtualPoker_Web_OnlineLogin_BarCodeField_GameInfo_Validations

	#Scenario: Verify functionalities of VirtualPoker module like barcode and slip id details and the  gameinfo page
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module,login via online method, place bet, get the slip ID, valid and invalid input 
    #When Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fileds in the slip generator
    #Then Web: Verify the field with valid slip id while the bets are not placed for specific slip id by the user and verify the result
    #Then Web: Verify the field with invalid slip id and verify the validation message is displayed to user
    #Then Web: Verify the game info page is been loading 
	#Then Web: Verify the search slip after logout and search for valid and invalid inputs
	  
		
	
	#VirtualPoker_Web_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module like hide and unhide the BettingSlip and AllDraws sections and the open and close balance after login and logout and re-login with successfull and cancelling bets combinations validations
    #Given  Web: Chrome browser, suribet website valid URL, virtual roulette module,login via online method, betslip arrow, all draws arrows, login balance and logout balance
    #When Web: note the login balance, and click on the hide and un hide buttons located on all draws and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user
    #Then Web: Validate the hide and un-hide functionality under alldraws and betting slip sections and the balance validations after relogin to the suribet account
	
	
	
	#VirtualPoker_Web_OnlineLogin_BetPlace_AfterTimeLapse_RemoveAllButton_Validations
	
	#Scenario: Verify the place bet within '30' to '0'th seconds and after time lapse, select bet and do not place bet till the time lapse and validate the remove button availabliy to user
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, valid logins, statitcs table, count time, valid error user message, remove all button
    #When Web: Place bet between the last 30secounds count down time, and after time lapse place bet
    #When Web: Select bet and wait for the timelapse and the bets selected is overlapped by the remove all button and clear all button is available to user to kill the previous bets selected when the selected draw has already started
    #Then Web: Validate the bet placed between last 30 seconds is successfull, and after time lapse placing bets results in valid error message is displayed to user
    #Then Web: Validate the selected bets when the draw started will be overlapped by removal all button and valid user message  

	
	
	
	#VirtualPoker_Web_StatitcsData_Validations
	
	#Scenario: Verify the Statitcs like count increasing by the win combinations and all the win combination values are displayed to user and not null 
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module, valid logins, statitcs table, count time
    #When Web: Fetch the staticts table details before the time lapse, after the successfull bet, validate the statitcs results
    #Then Web: Validate the win combination count is updated in the statitcs table after each round   
	
	
	
	#VirtualPoker_Web_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module like timelapse cancel bet balance updation in online login mode of transactions
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module,login via online method, place bet, time period, balance
    #When Web: Place bet and cancel bet after "1min" and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance
    #Then Web: Validate balance refresh functionality and bet status with time period lapse for cancellation of bets via online login mode of transcations
	
	
	#VirtualPoker_Web_ChipDenominations_Validations
	
	#Scenario: Verify functionalities of VirtualPoker module's chip denomination adding, double and undo buttons 
    #Given  Web: Chrome browser, suribet website valid URL, virtual poker module,login via online method, chips, double, stake and undo
    #When Web: Select the future draw and selecting chip and place on any winning combinations and add addidtional denomation to the same and select the 2X button and at last undo the chips
    #Then Web: Validate the chip deomination additions on top of same chip and the double and undo functionalities.
	
	# Poker_ResultVerification_With_ExternalSite
	Scenario: Once the result displayed, copy the elements from the result section and input those elements in external poker site and check the winning combination
    Given  Web: A
    When Web: B
    Then Web: C
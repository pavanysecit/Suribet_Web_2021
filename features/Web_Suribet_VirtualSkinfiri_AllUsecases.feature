Feature: Test all the functionalities of VirtualSkinfiri module in Suribet Website on Web version
   

   # VirtualSkinfiri_Web_Login_Home_Validations
   
     #Scenario: Load the suribet website with valid URL, verify login scenarios and home button functionalities
    #Given  Web: Chrome browser, suribet website valid URL, valid & invalid login details and home button
    #When Web: Open the chrome browser, Enter the valid suribet URL, Click on VirtualSkinfiri module link, enter valid and invalid login details and click on home button
    #Then Web: User should be blocked from logging to the suribet website
#	And Web: User should able to see the VirtualSkinfiri module link in the home page and should see VirtualSkinfiri module home page after login & clicking on VirtualSkinfiri module
#	And Web: User should be navigated to the Home page of suribet website after clicking on home link
	 
	 
	 
	 	# VirtualSkinfiri_Web_AccountSummaryPage_Validations
	
#	Scenario: Verify the Account Summary page like paginations, number of rows selections, filter with dates and modules, filetring bet details
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module, valid logins, Account summary page 
    #When Web: Navigate to account summary page and modify the search pattern like date module row length 
#	Then Web: Result has to be displayed as per the search combinations 
	
	
	     # VirtualSkinfiri_Web_PlaceBet_CancelBet_BalanceRefresh_Validations

#	Scenario: Verify basic functionalities of VirtualSkinfiri module like Place bet can cancel the bet with the balance being updated for every bets
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, number table, stake amount, place bet, balance, cancel bet and print slip
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, place bet with selecting the stake amount
    #Then Web:  Balance has to decuted respective with bet placed, and print slip has to be geneated
#	And Web: Cancel the bet placed and check with the same amount being updated to the main balance
#	And Web: Validation successful message for placing bet should be generated
#	And Web: After cancelling the placed bet validation message has to be generated and stake balance has be added to main account balance
	
	
	
		#   VirtualSkinfiri_Web_SessionExpiry_Validations
	
#	Scenario: Verify basic functionalities of VirtualSkinfiri module like session expiry time duration validations
    #Given  Web: Chrome browser, suribet website valid URL, idel time duration
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, be ideal for about 20 minutes 
    #Then Web:  Place bet after the time duration, and validate the the session is expired and the user message is prompted and displayed to user to re login and establish new session 

	
	
	      # VirtualSkinfiri_Web_CardLogin_RemoveBets_AccountDeatilsPageCancelBet_BalanceRefresh_Validation

#	Scenario: Verify functionalities of VirtualSkinfiri module like login via gmail and switch to card, place bet, remove certain placed bet and later cancel via account details page
    #Given  Web: Chrome browser, suribet website valid URL, Draw details, skinfiri table, stake amount, place bet, balance, no of rows, account details page, cancel bet alert, cancel the bet
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, change to card mode of payment
    #Then Web:  Place as many as bets and check the bet rows before and after selecting the remove options
#	And Web: Forward and confirm the bets and validate the user message and direct to account details page
#	And Web: Cancel the placed bet and accept all the alerts with validation messages and verify the amount being deposited and refreshed after cancelling the bets in Virtual skinfiri module
	
	
			# VirtualSkinfiri_Web_CardLogin_TimeLapseCancelBets_BalanceUpdate_Validations
	
#	Scenario: Verify functionalities of VirtualSkinfiri module like timelapse cancel bet balance updation in card login mode of transactions
    #Given  Web: Chrome browser, suribet website valid URL, virtual VirtualSkinfiri module, place bet, time period, balance
    #When Web: Place bet and cancel bet after 1min and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance
    #Then Web: Validate balance refresh functionality and bet status with time period for cancelation of bets via card login
	
	
	
		# VirtualSkinfiri_Web_ClearBets_IncreaseOrDecrease_Stake_Validations
	
    #Scenario: Verify basic functionalities of VirtualSkinfiri module like clear All, clear individual, increase or decrease stake amount individually, 	
    #Given  Web: Chrome browser, suribet website valid URL, skinfiri module, place bet, Clear All, clear individually, stake increase or decrease individually
    #When Web: Login to suribet website with valid login details, redirect to VirtualSkinfiri module link and place bet 
    #Then Web: Cancel individually and clear all, verify the stake amount and verify the changes respectively 
	
	
	
		# VirtualSkinfiri_Web_Draw_BetNumbers_BetAmount_Verifications
	
#	Scenario: Verify basic functionalities of VirtualSkinfiri module like verifying current draw, bet numbers, place a bet without login, verify minimum bet, cross verify selected bet numbers and stake amount
    #Given  Web: Chrome browser, suribet website valid URL, current draw, bet numbers, mimimum bet, stake amount and validation messages
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, cross check current draw , bet numbers and validation message for mimimum bet & stake amount calculations
    #Then Web:  System should show Current Draw#, Date, Time in the VirtualSkinfiri module
#	And Web: Selected Draw No#, Time on the VirtualSkinfiri module screen
#	And Web: Validation message for placing bet without login to the website
#	And Web: System should not allow user to enter the stake amount in whole numbers, It should be autocalculated in VirtualSkinfiri module
	
	
	
		# VirtualSkinfiri_Web_MultipleDraws_PlaceBet_CancelBet_Validations
	
#	 Scenario: Verify functionalities of VirtualSkinfiri module like Multiple draws place bet and cancel bet individually and check the balance after cancelling
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module, place bet selecting multiple draws 
    #When Web: Cancel slip according to draw numbers individually and validate the balance and cancel slip remaining draws
    #Then Web: Validate balance refresh functionality and added to main balance after cancelling the slips
	
	
				# VirtualSkinfiri_Web_OnlineLogin_BarCodeField_GameInfo_Validations

#	 Scenario: Verify functionalities of VirtualSkinfiri module like barcode and slip id details
    #Given  Web: Chrome browser, suribet website valid URL, Virtual Skinfiri module,login via online method, place bet, get the slip ID, valid and invalid input 
    #When Web: Place bet and get the slip id and cancel the bet and input the same slip id in barcode field and verify the status and the fields in the slip generator
    #Then Web: Verify the field with valid slip id while the bets are not placed for specific slip id by the user and verify the result
    #Then Web: Verify the field with invalid slip id and verify the validation message is displayed to user
    #Then Web: Verify the game info page is been loading 
#	Then Web: Verify the search slip after logout and search for valid and invalid inputs
	
	
	
		# VirtualSkinfiri_Web_OnlineLogin_HideAndUnhide_BettingSlip_AllDraws_AccountOpenAndCloseBalance_Validations
	
#	Scenario: Verify functionalities of VirtualSkinfiri module like hide and unhide the BettingSlip and AllDraws sections and the open and close balance after login and logout and re-login with successfull and cancelling bets combinations validations
    #Given  Web: Chrome browser, suribet website valid URL, virtual VirtualSkinfiri module,login via online method, betslip arrow, all draws arrows, login balance and logout balance
   #When Web: note the login balance, and click on the hide and un hide buttons located on all draws and betting slip columns and place bets and check the balance deductions and logout and relogin and verify the balance reflected to the user
    #Then Web: Validate the hide and un-hide functionality under alldraws and betting slip sections and the balance validations after relogin to the suribet account
	
	
				# VirtualSkinfiri_Web_OnlineLogin_TimeLapseCancelBets_BalanceUpdate_Validations
	
#	 Scenario: Verify functionalities of VirtualSkinfiri module like timelapse cancel bet balance updation in online login mode of transactions
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module,login via online method, place bet, time period, balance
    #When Web: Place bet and cancel bet after '1min' and validate the message and check the bet status after time lapse cancellation in account summary tab and verify the updation of main balance
    #Then Web: Validate balance refresh functionality and bet status with time period lapse for cancelation of bets via online login mode of transcations
	
	
	
		# VirtualSkinfiri_Web_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations
	
    #Scenario: Verify functionalities of VirtualSkinfiri module like pay by card functionality
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module, place bet and pay the amount via card'PAY BY CARD' option
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link and place bet 
    #Then Web: Select 'PAY BY CARD' option and fill the card details and accept the pop-up
#		And Web: Cancel the bet by click on the Cancel Slip button and fill the card details 
#		And Web: Successfull cancelling bet user message is displayed to user in virtual skinfiri module
#		And Web: Again place bet and wait for 2mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button
	
	
	
			# VirtualSkinfiri_Web_BetTableDetails_BetSlipCrossVerification_Language_Validations
	    # As the design in the old UI have scripted as per the new design changes with roulette module as refrence once changed have to verify
	    
#		Scenario: Verify the bet details table above the skinfiri annimation screen, cross verify the bet selected in number table is same displayed on betting slip and the language change 
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module, valid logins, bet details table, bet place and bet won and updated bet tables  
    #When Web: Place bet and verify the details updated in the bet table and wait till the current bet is completed and bet table is updated with last bet details
    #Then Web: Verify the bet details table before place bet, after bet placed and once after current bet is completed 
    #Then Web: Cross verification of betting slip with the number table bet selection  
#		Then Web: Verify the language change reflects on the web page 
	
	
	
					# VirtualSkinfiri_Web_QuickPick_Replay_Next10Draws_SelectAndDeselectBet_Validations
	
#	 Scenario: Verify functionalities of VirtualSkinfiri module like selecting and deselecting the bet and the quick picks and selecting the next upcoming 10 draws with replay button options
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module,login via online method, place bet, quickpick 5 and 10, replay button and next 10 draws
    #When Web: Select the quick pick options like 5 and 10 and the next 10 draws, later select some bet numbers and again click on the same to deselect from the betting tray, finally place bet and select the replay button 
    #Then Web: Validate the quick pick options as same random bet numbers are selected at the betting tray
     #Then Web: Validate the next 10 draws as the next draws are selected to place bet at the betting tray
     #Then Web: Verify the select and deselect options and place bet successfully and validate the replay button option
     
	
	
		#  VirtualSkinfiri_Web_ChipDenominations_Addition_RefreshPage_Validations
	
#	Scenario: Verify functionalities of VirtualSkinfiri module like chip denominations, chip bets lock and unlock state on number table numbers under refreshpage, navigation to other sites and after re-login to virtual skinfiri module having previously selected draw num
    #Given  Web: Chrome browser, suribet website valid URL, virtual skinfiri module, place bet, chip denominations, lock and unlock state of chip denominations, refresh page, login and logout
    #When Web: Login to suribet website with valid login details, select virtual skinfiri module link 
    #Then Web: Select some numbers on number table and add the same chip denominations individually and the total stake input and place bet 
#	And Web: After placed bet check for the chips are still in lock condition for selected bets later navigate to other tabs and refresh virtual skinfiri game page, logout and again login with same credentails 
#	And Web: Redirect to same draw num and verify whether previously placed bet are still in lock with selected numbers on virtual skinfiri number table 
	
	
	
	
		  #  VirtualSkinfiri_Web_PreviousResult_Validations
	  
	
#	  Scenario: Verify basic functionalities of VirtualSkinfiri module last ten draw results
    #Given  Web: Chrome browser, suribet website valid URL, bet placing, last ten draw results
    #When Web: Login to suribet website with valid login details, Click on VirtualSkinfiri module link, check the recent results section	
    #Then Web:  Validate the current draw and the results shows the previous 10 draw results with time id and draw num being displayed to user
	  
	
	
	   #	VirtualSkinfiri_Web_Search_DrawResult_Validations
	
#	  Scenario: Verify functionalities of VirtualSkinfiri module like draw result tab under various inputs
    #Given  Web: Chrome browser, suribet website valid URL, VirtualSkinfiri module, click on search draw tab and verify the previous draw results are displayed to the user
    #When Web: Change the dates and verify under search is selected, all draw results are displayed to user in draw result tabs with and without win combinations
    #Then Web: Search with blank draw num and verify the pervious draw is displayed, with bet number and bet color 
  #	And Web: Search with invalid draw numbers and verify the validation results for '0' and above '479' invalid draw results 
	 
	  
	  
	  
	  
	   
		 		# VirtualSkinfiri_Web_WithoutLoginPlaceBet_Validations
	
#	  Scenario: Verify functionalities of Virtual Skinfiri module like place bet under logout condition, rebet validations and quickpick
    #Given  Web: Chrome browser, suribet website valid URL, skinfiri module, place bet, rebet, and quickpick validations messages
    #When Web: direct to the module and place bet in loogout condition with and without bets and try to rebet under logout conditions
    #Then Web: Validate the appropriate user message displayed bet placing under logout conditions
	
	
	
	
		# VirtualSkinfiri_Web_WithoutLogin_MultipleDraws_PaybyCard_Place_Cancel_Bets_TimeLapseCancelBets_Validations
	
    #Scenario: Verify functionalities of VirtualSkinfiri module like pay by card functionality without login and placing multiple draws
    #Given  Web: Chrome browser, suribet website valid URL, Virtual Skinfiri module, place bet and pay the amount via card'PAY BY CARD' option, multiple draaws and timelapse user message
    #When Web: Login to suribet website with valid login details, Click on Virtual Skinfiri module link, multiple draws, place bet 
    #Then Web: Select 'PAY BY CARD' option and fill the card details and accept the pop-up
#	  And Web: Cancel the single bet by click on the Cancel Slip button and fill the card details 
#	  And Web: Again place bet and wait for 2mins and cancel the bets and check for 'time lapse' validation message and visibility of the cancel slip button in the multiple draws selection
	
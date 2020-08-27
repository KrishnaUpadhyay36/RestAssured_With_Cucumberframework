Feature: Validating place Api's

@AddPlace @Regression
Scenario Outline: Validate AddPlace Api
 	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceApi" with "POST" http request
	Then Place "Added" successfully with status code "200"
	And Response body "status" is "OK"
	And Verify created place_id maps to "<name>" using "GetPlaceApi"
	
Examples:
	| name    | language | address 			   |
	| ABhouse | French   | Shaligram Signature |
#	| BBhouse | Spanish  | Sea Class Center    |
	
@UpdatePlace @Regression
Scenario: Validate the UpdatePlaceApi is working Correctly
	Given Given the place_id and key update the address "Bellandur" 
	When user calls "UpdatePlaceApi" with "PUT" http request
	Then Place "Updated" successfully with status code "200"
	And Verify created Adress maps to the "Bellandur"
	
@DeletePlace @Regression
Scenario: validate DeletePlaceApi is working correctly
	Given Give payload for DeletePlaceApi
	When user calls "DeletePlaceApi" with "POST" http request
	Then Place "Added" successfully with status code "200"
	And Response body "status" is "OK"
	

package stepDefinations;

import io.cucumber.java.Before;

public class Hello {

	@Before("@DeletePlace")
	public void DeletePreconditions() throws Throwable
	{
		System.out.println("I am inside Hooks");
		AddPlaceDefination obj = new AddPlaceDefination();
		if(AddPlaceDefination.placeId==null) {
		
		obj.add_place_payload_with("Krishna","Gujarati","A.K road");
		obj.user_calls_with_http_request("AddPlaceApi", "POST");
		obj.verify_created_place_id_maps_to_using("Krishna", "GetPlaceApi");
		}
	}
	
	
}

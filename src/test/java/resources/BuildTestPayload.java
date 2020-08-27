package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class BuildTestPayload {

	public AddPlace TestPayload(String name, String language, String address)
	{
		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setName(name);
		a.setPhone_number("(+91) 975851551571");
		a.setWebsite("http://google.com");
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		
		List<String> addList = new ArrayList <String>();
		addList.add("Address");
		addList.add("Home");
		a.setTypes(addList);
		
		return a;
	}
	
	public String deletePlacePayload(String placeID)
	{
		String payload = "{\r\n" + "\"place_id\":\""+placeID+"\"\r\n" + "}\r\n" + "";
		return payload;
	}
	
	public String UpdatePlacePayload(String address, String PlaceId)
	{
		String payload = "{\r\n" + 
				"\"place_id\":\""+PlaceId+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"";
		return payload;
	}
	
}


package resources;

public enum APIResources {

	AddPlaceApi("/maps/api/place/add/json"), 
	GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json"),
	UpdatePlaceApi("/maps/api/place/update/json");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource = resource;
	}
	public String GetResource()
	{
		return resource;
	}
	
}

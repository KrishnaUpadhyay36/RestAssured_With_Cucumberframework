package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	public RequestSpecification setRequestSpecification() throws IOException
	{
		if(requestSpecification==null) {
		PrintStream log = new PrintStream(new FileOutputStream("Logs.txt"));
		requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalvalue("BaseUri")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
		return requestSpecification;
		}
		return requestSpecification;
	}
	
	public static String getGlobalvalue(String value) throws IOException
	{
		Properties property = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\krisupad\\Documents\\REST Assured\\MavenJavaRestAssured\\src\\test\\java\\resources\\global.properties");
		property.load(file);
		return property.getProperty(value);
	}
	
	
	public ResponseSpecification setResponseSpecification()
	{
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		return responseSpecification;
	}

	public String GetJsonPath(Response response, String key)
	{
		String res = response.asString();
		System.out.println("json Response: \n"+res);
		JsonPath js = new JsonPath(res);
		System.out.println("Key that we got is: " + js.get(key));
		return js.get(key);
	}
	
}

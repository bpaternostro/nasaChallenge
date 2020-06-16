package nasa.challenge;

import junit.framework.TestCase;
import nasa.interfaces.JsonFromApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestBase extends TestCase
{
	Retrofit retrofit;			
	JsonFromApi jsonFromApi; 
	Global glo;	
	
	public TestBase() {
		retrofit= new Retrofit.Builder()
				.baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
				.addConverterFactory(GsonConverterFactory.create())    			
				.build();
		jsonFromApi= retrofit.create(JsonFromApi.class);
		glo = new Global();
	}
		
	
}

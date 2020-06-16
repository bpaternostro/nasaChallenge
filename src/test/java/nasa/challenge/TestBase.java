package nasa.challenge;

import nasa.interfaces.JsonFromApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestBase {
	static Retrofit retrofit;
	static JsonFromApi jsonFromApi;
	static Global glo;

	public TestBase() {
		retrofit = new Retrofit.Builder().baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		jsonFromApi = retrofit.create(JsonFromApi.class);
		glo = new Global();

	}

}

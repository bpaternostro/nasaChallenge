package nasa.challenge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import nasa.util.Constant;
import retrofit2.Call;

public class NasaTest extends TestBase {

	String earthDateCheckMsg = "It was impossible to catch earth_date.";

	
	// 1-Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
	@Parameters({ "sol", "page" })
	@Test
	public void test1000MartianSol(int sol, int page) {
		Call<ArrayPhotos> call = jsonFromApi.getPhotos(sol, page, Constant.TOKEN);
		List<Photo> photos = glo.getPhotos(call);
		glo.listAllPhotos(photos);

	};

	// 2-Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal
	// to 1000 Martian sol.
	@Parameters({ "sol", "page" })
	@Test
	public void test1000MartianSolWithEarthDate(int sol, int page) {

		// getting the earh date from martian sol 1000
		Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(sol, page, Constant.TOKEN);
		String earth_date = glo.getEarthDateMartianSol(callAux);

		if (earth_date != null) {
			Call<ArrayPhotos> call = jsonFromApi.getPhotosByEarthDate(earth_date, page, Constant.TOKEN);
			List<Photo> photos = glo.getPhotos(call);
			glo.listAllPhotos(photos);
		} else {
			log.info("Test - test1000MartianSolWithEarthDate: " + earthDateCheckMsg);
			Assert.assertTrue(false, earthDateCheckMsg);
		}

	}

	// 3-Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000
	// sol and on Earth date equal to 1000 Martian sol.
	@Parameters({ "sol", "page" })
	@Test
	public void testComparisonList(int sol, int page) {

		String earth_date = null;
		List<Photo> photos = null;
		List<Photo> photos2 = null;

		Call<ArrayPhotos> call1 = jsonFromApi.getPhotos(sol, page, Constant.TOKEN);
		photos = glo.getPhotos(call1);

		// getting the earh date from martian sol 1000
		Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(sol, page, Constant.TOKEN);
		earth_date = glo.getEarthDateMartianSol(callAux);

		if (earth_date != null) {
			Call<ArrayPhotos> call2 = jsonFromApi.getPhotosByEarthDate(earth_date, page, Constant.TOKEN);
			photos2 = glo.getPhotos(call2);
		} else {
			log.info("Test - testComparisonList: " + earthDateCheckMsg);
			Assert.assertTrue(false, earthDateCheckMsg);
		}

		// Assuming that both list should be equals
		Boolean result = photos.equals(photos2);
		String msg = "Test - testComparisonList: Checking List of photos " + result;
		log.info(msg);
		Assert.assertTrue(result, msg);

	}

	// 4-Validate that the amounts of pictures that each "Curiosity" camera took on
	// 1000 Mars sol is not greater than 10 times the amount taken by other cameras
	// on the same date.
	@Test
	public void testValidateAmountOfPhotos(int sol, int page) {

		String earth_date = null;
		String cameraToCompare = "curiosity";
		List<Photo> photos = null;
		Map<String, Long> amounts = new HashMap<String, Long>();

		// getting the earh date from martian sol 1000
		Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(sol, page, Constant.TOKEN);
		earth_date = glo.getEarthDateMartianSol(callAux);

		if (earth_date == null) {
			log.info("Test - testValidateAmountOfPhotos: " + earthDateCheckMsg);
			Assert.assertTrue(false, earthDateCheckMsg);
			return;
		}

		for (String camera : Constant.CAMERAS) {
			Call<ArrayPhotos> call2 = jsonFromApi.getPhotosByCamera(camera, sol, earth_date, Constant.TOKEN);
			photos = glo.getAllPhotos(call2);
			amounts.put(camera, glo.getTotalAmountPhotos(photos));
		}

		Boolean result = glo.checkAmounts(amounts, cameraToCompare);
		String msg = "Test - testValidateAmountOfPhotos: Checking amount of photos " + result;
		log.info(msg);
		Assert.assertTrue(result, msg);

	}

}


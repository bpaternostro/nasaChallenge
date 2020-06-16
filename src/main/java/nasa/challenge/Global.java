package nasa.challenge;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import nasa.util.Constant;
import retrofit2.Call;

public class Global {

	static Logger log = Logger.getLogger(Global.class.getName());

	public List<Photo> getPhotos(Call<ArrayPhotos> call) {

		List<Photo> photosFinal = null;
		try {
			List<Photo> photos = call.execute().body().getPhotos();
			photos.sort(Comparator.comparingInt(Photo::getId));
			photosFinal = photos.subList(0, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return photosFinal;
	}

	public List<Photo> getAllPhotos(Call<ArrayPhotos> call) {

		List<Photo> photos = null;
		try {
			photos = call.execute().body().getPhotos();
			photos.sort(Comparator.comparingInt(Photo::getId));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return photos;
	}

	public String getEarthDateMartianSol(Call<ArrayPhotos> call) {
		String earth_date = null;
		try {
			earth_date = call.execute().body().getPhotos().get(0).getEarthDate();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return earth_date;
	}

	public void listAllPhotos(List<Photo> photos) {
		for (Photo item : photos) {
			log.info(item.getId());

		}
		log.info("---------------------------------------------");
	}

	public long getTotalAmountPhotos(List<Photo> list) {
		long sum = 0;

		for (Photo item : list) {
			Rover ro = item.getRover();
			sum = sum + ro.getTotalPhotos();
		}

		return sum;
	}

	public Boolean checkAmounts(Map<String, Long> map, String cameraToCompare) {

		Boolean validation = false;

		long amountToCompare = map.get(cameraToCompare);

		for (String item : Constant.CAMERAS) {
			if (item != cameraToCompare) {
				if (amountToCompare / 10 <= map.get(item)) {
					validation = true;
				} else {
					log.info(item + " has not passed 10 times validation");
					return false;
				}
			}
		}

		return validation;
	}

}

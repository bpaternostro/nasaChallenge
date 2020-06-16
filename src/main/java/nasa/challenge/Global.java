package nasa.challenge;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

import nasa.util.Constant;
import retrofit2.Call;

public class Global {

	final static Logger log = Logger.getLogger(Global.class.getName());

	public List<Photo> getPhotos(Call<ArrayPhotos> call, int init,int end) {

		List<Photo> photos = null;
		try {
			photos = call.execute().body().getPhotos();
		} catch (IOException e) {
			log.error("Error on method getPhotos: " + e.getMessage());
			e.printStackTrace();
		}

		List<Photo> photosFinal = photos.stream().sorted(Comparator.comparingInt(Photo::getId))
				.collect(Collectors.toList());
		
		return photosFinal.subList(init, end);
	}

	public List<Photo> getAllPhotos(Call<ArrayPhotos> call) {

		List<Photo> photos = null;
		try {
			photos = call.execute().body().getPhotos();

		} catch (IOException e) {
			log.error("Error on method getAllPhotos: " + e.getMessage());
			e.printStackTrace();
		}

		List<Photo> photosFinal = photos.stream().sorted(Comparator.comparingInt(Photo::getId))
				.collect(Collectors.toList());

		return photosFinal;
	}

	public String getEarthDateMartianSol(Call<ArrayPhotos> call) {
		String earth_date = null;
		try {
			earth_date = call.execute().body().getPhotos().get(0).getEarthDate();
		} catch (IOException e) {
			log.error("Error on method getEarthDateMartianSol: " + e.getMessage());
			e.printStackTrace();
		}

		return earth_date;
	}

	public void listAllPhotos(List<Photo> photos) {
		for (Photo item : photos) {
			log.info("Photo with id:" + item.getId() + " has source of " + item.getImgSrc());

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
					log.info(item + " has not passed 10 times validation in checkAmounts method.");
					return false;
				}
			}
		}

		return validation;
	}

}

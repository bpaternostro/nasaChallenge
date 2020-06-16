package nasa.interfaces;
import nasa.challenge.ArrayPhotos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonFromApi {
	
	@GET("rovers/curiosity/photos")
	Call<ArrayPhotos> getPhotos(
			@Query("sol") int sol,
			@Query("page") int page,
			@Query("api_key") String token);
	
	@GET("rovers/curiosity/photos")
	Call<ArrayPhotos> getPhotosByEarthDate(
			@Query("earth_date") String earth_date,
			@Query("page") int page,
			@Query("api_key") String token);
	
	@GET("rovers/{camera}/photos")
	Call<ArrayPhotos> getPhotosByCamera(
			@Path("camera") String name,
			@Query("sol") int sol,
			@Query("earth_date") String earth_date,			
			@Query("api_key") String token);
}

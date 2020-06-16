package nasa.challenge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import nasa.util.Constant;
import retrofit2.Call;

public class NasaTest 
extends TestBase
{
		

	//prueba desde Intellij
    //1-Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
	@Test
    public void test1000MartianSol ()
    {
    	Call<ArrayPhotos> call = jsonFromApi.getPhotos(Constant.sol,Constant.page,Constant.token);
    	List<Photo> photos = glo.getPhotos(call); 
    	glo.listAllPhotos(photos);
    	    	    	    	
    };
    
    
    //2-Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
    @Test
    public void test1000MartianSolWithEarthDate ()
    {
    	     	    	
    	//getting the earh date from martian sol 1000
    	Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(Constant.sol,Constant.page,Constant.token);
    	String earth_date = glo.getEarthDateMartianSol(callAux);
		    	  	    	
		if(earth_date!=null) {				    	
			Call<ArrayPhotos> call = jsonFromApi.getPhotosByEarthDate(earth_date,Constant.page,Constant.token);
			List<Photo> photos = glo.getPhotos(call); 
	    	glo.listAllPhotos(photos);		
		}
		else
		{
			assertTrue("It was impossible to catch earth_date.",false);
		}
    	    	
    }
    
    //3-Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
   	@Test
    public void testComparisonList ()
    {
    	     	
    	
    	String earth_date = null;
    	List<Photo> photos = null;
    	List<Photo> photos2 = null;
    	
    	Call<ArrayPhotos> call1 = jsonFromApi.getPhotos(Constant.sol,Constant.page,Constant.token);
    	photos = glo.getPhotos(call1);
    	
    	//getting the earh date from martian sol 1000
    	Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(Constant.sol,Constant.page,Constant.token);
    	earth_date = glo.getEarthDateMartianSol(callAux);
		    	  	    	
		if(earth_date!=null) {				    	
			Call<ArrayPhotos> call2 = jsonFromApi.getPhotosByEarthDate(earth_date,Constant.page,Constant.token);
	    	photos2 = glo.getPhotos(call2); 	    		
		}
		else
		{
			assertTrue("It was impossible to catch earth_date.",false);
		}
		
		//Assuming that both list should be equals 		
		assertTrue("Checking List of photos",photos.equals(photos2));		
  	    	
    }    
        
    //4-Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.
    @Test
	public void testValidateAmountOfPhotos ()
    {
    	     	
    	String earth_date = null;
    	String cameraToCompare = "curiosity";
    	List<Photo> photos = null;    	
    	Map<String, Long> amounts= new HashMap<String, Long>();
    	
    	//getting the earh date from martian sol 1000
    	Call<ArrayPhotos> callAux = jsonFromApi.getPhotos(Constant.sol,Constant.page,Constant.token);
    	earth_date = glo.getEarthDateMartianSol(callAux);
		    	  	    	
    	if(earth_date==null) {
			assertTrue("It was impossible to catch earth_date.",false);
			return;
		}
    	
    	for(String camera:Constant.cameras) {    		    								    	
			Call<ArrayPhotos> call2 = jsonFromApi.getPhotosByCamera(camera, Constant.sol, earth_date,Constant.token);
		    photos = glo.getAllPhotos(call2); 	
		    amounts.put(camera, glo.getTotalAmountPhotos(photos));
    	}
		  	    
  	    assertTrue("Checking amount of photos: ",glo.checkAmounts(amounts,cameraToCompare));
  	      	    
    }
    
}

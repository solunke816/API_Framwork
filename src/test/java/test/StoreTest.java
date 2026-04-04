package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.StoreEndPoint;
import io.restassured.response.Response;
import payload.Store;

public class StoreTest {
	
	Faker faker;
	Store storepaylod;
	
	@BeforeTest
	public void setup() {
		//for random data Generate
		faker=new Faker();
		storepaylod=new Store();
		
		storepaylod.setId(faker.number().numberBetween(1, 10));
		storepaylod.setPetId(faker.number().numberBetween(100, 1000));
		storepaylod.setQuantity(faker.number().numberBetween(1, 10));
		
		//Time stamp use
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
	    String shipDate = sdf.format(new Date());
		storepaylod.setShipDate(shipDate);
		
		//used string to pass random status
		String []status= {"placed","approved","delivered "};
		storepaylod.setStatus(status[new Random().nextInt(status.length)]);
		
		Random random = new Random();
	    storepaylod.setComplete(random.nextBoolean());
	    
		
		
	}

	@Test(priority = 1)
	void testOrder() {
		Response respons=StoreEndPoint.Place_order(storepaylod);
		respons.then().log().all();
		Assert.assertEquals(respons.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	void inventory() {
		Response respons=StoreEndPoint.store_inventory(storepaylod);
		respons.then().log().all();
		Assert.assertEquals(respons.getStatusCode(), 200);
	}
	@Test(priority = 3)
	void store_order() {
		Response respons=StoreEndPoint.store_order(storepaylod);
		respons.then().log().all();
		Assert.assertEquals(respons.getStatusCode(), 200);
	}
   @Test(priority = 4)
	void delete_order() {
		Response respons=StoreEndPoint.delete(storepaylod);
		respons.then().log().all();
		Assert.assertEquals(respons.getStatusCode(), 200);
	}
}

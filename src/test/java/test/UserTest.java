package test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndPoint;
import io.restassured.response.Response;
import payload.User;

public class UserTest {

	//for random data 
	Faker fakr;
	User userpayload;
	
	 @BeforeTest
	 public void setup() {
		
	 fakr = new Faker();
	 userpayload = new User();
	
	 userpayload.setId(fakr.idNumber().hashCode());
	 userpayload.setUsername(fakr.name().username());
	 userpayload.setFirstName(fakr.name().firstName());
	 userpayload.setLastName(fakr.name().lastName());
	 userpayload.setEmail(fakr.internet().safeEmailAddress());
	 userpayload.setPassword(fakr.internet().password(5,10));
	 userpayload.setPhone(fakr.phoneNumber().phoneNumber());
	 
		
	}
	 @Test(priority = 1)
	 void testpost() {
		 Response respons=UserEndPoint.CreatUser(userpayload);
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
		 
	 }
	 
	 @Test(priority = 2)
	 void testGetuserByName() {
		 Response respons=UserEndPoint.readUser(this.userpayload.getUsername());
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
	 }
	 
	 @Test (priority=3)
	void testUpdateByname(){
		 userpayload.setFirstName(fakr.name().firstName());
		 userpayload.setLastName(fakr.name().lastName());
		 userpayload.setEmail(fakr.internet().safeEmailAddress());
		 
		 Response respons=UserEndPoint.updateUser(this.userpayload.getUsername(), userpayload);
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
	 }
	 @Test(priority=4)
	 void testDeletbByname() {
		 Response respons=UserEndPoint.detetuser(this.userpayload.getUsername());
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
	 }

}

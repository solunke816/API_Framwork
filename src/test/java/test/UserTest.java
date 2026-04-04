package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
     public Logger log ;//for log
	
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
	 
	 log=LogManager.getLogger(this.getClass());
	 log.debug("debugging.....");
		
	}
	 @Test(priority = 1)
	 
	 void testpost() {
		 
		 log.info("***Creating_User***");
		 
		 Response respons=UserEndPoint.CreatUser(userpayload);
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
		 
		 log.info("***User_Created***");
		 
	 }
	 
	 @Test(priority = 2)
	 void testGetuserByName() {
		 
		 log.info("***Geting_User***");
		 
		 Response respons=UserEndPoint.readUser(this.userpayload.getUsername());
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
		 
		 log.info("***User_details***");
	 }
	 
	 @Test (priority=3)
	void testUpdateByname(){
		 
		 log.info("***Updating_User***");
		 
		 userpayload.setFirstName(fakr.name().firstName());
		 userpayload.setLastName(fakr.name().lastName());
		 userpayload.setEmail(fakr.internet().safeEmailAddress());
		 
		 Response respons=UserEndPoint.updateUser(this.userpayload.getUsername(), userpayload);
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
		 
		 log.info("***User_Updated_done***");
		 
	 }
	 @Test(priority=4)
	 void testDeletbByname() { 
		 
		 log.info("***Deleting_User***");
		 
		 Response respons=UserEndPoint.detetuser(this.userpayload.getUsername());
		 respons.then().log().all();
		 
		 Assert.assertEquals(respons.getStatusCode(), 200);
		 
		 log.info("***User_Deleted***");
	 }

}

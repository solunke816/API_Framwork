package endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

public class UserEndPoint {
	
	// Created for perform Create, Read, Update, Delete requests t the user API
	
   public static Response CreatUser(User payload) {
	   
	   Response respons=given()
			   
	   .contentType(ContentType.JSON)
	   .accept(ContentType.JSON)
	   .body(payload)
	   
				.when().post(Route.post_url);

		return respons;
	   
   }
   public static Response readUser(String userName){
	  
	  Response response=given()
	  
	  .pathParam("username", userName)
	  
				.when().get(Route.get_url);

		return response;
	   
   }
   
   public static Response  updateUser(String userName, User payload){
	   
	   Response response=given()
			  .contentType(ContentType.JSON)
			  .accept(ContentType.JSON)
			  .pathParam("username",userName)
			  .body(payload)
	          
				.when().put(Route.update_url);

		return response;
   }
   
   public static Response detetuser(String usrName){
	   
	   Response response=given()
			  
				.pathParam("username",usrName)

				.when().delete(Route.delete_url);
		return response;
	}
}

package endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Store;

public class StoreEndPoint {
	
	//place order

	public static Response Place_order(Store paylod) {
		Response respons=given()
		
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(paylod)
				     .when().post(Route.post_store_url);
		
		return respons;
	}
	//store inventory
	public static Response store_inventory(Store paylod) {
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(paylod)
		.when().get(Route.get_inventory);
		return response;
	}
	
	//get order using orderId
	public static Response store_order(Store paylod) {
		Response resp=given()
				.pathParam("orderId", paylod.getId())
		        .when().get(Route.get_order);
		return resp;
	}
	//delete order using id
	public static Response delete(Store payload) {
		Response resp=given()
				.pathParam("orderId", payload.getId())
				.when().delete(Route.delete_order);
		return resp;
	}
	
}

package api;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;


public class RegisterAPI extends BaseAPI{

	@Test
	public void regisAPISuccess() {
		Faker faker = new Faker();
		String username = faker.name().username();
		
		String payload = "{\"username\": \""+username+"\",\"password\": \"Password!123\","
				+ "    \"email\": \""+username+"@gmail.com\",\"phonenumber\": \"080989999\"}";
		
		Response responseRegis = given().spec(commonJsonSpec).body(payload).when().post("/register");
		assertEquals(responseRegis.statusCode(), 201);
	}
	
	@Test
	public void regisAPIFailedSameEmail() {
		String message = "";
		Faker faker = new Faker();
		String username = faker.name().username();
		
		String payload = "{\"username\": \""+username+"\",\"password\": \"Password!123\","
				+ "    \"email\": \"alamatemail@gmail.com\",\"phonenumber\": \"080989999\"}";
		
		Response responseRegisFailedEmail = given().spec(commonJsonSpec).body(payload).when().post("/register");
		assertEquals(responseRegisFailedEmail.statusCode(), 400);
		
		message = responseRegisFailedEmail.jsonPath().get("message");
		System.out.println("ERROR KENAPA ? "+ ANSI_WHITE_BACKGROUND + ANSI_PURPLE  + message + "\n" + ANSI_RESET);
	}
	
	@Test
	public void regisAPIFailedSameUsername() {
		String message = "";
		Faker faker = new Faker();
		String email = faker.internet().safeEmailAddress();
		
		String payload = "{\"username\": \"nama.saya\",\"password\": \"Password!123\","
				+ "    \"email\": \""+email+"\",\"phonenumber\": \"080989999\"}";
		
		Response responseRegisFailedUsername = given().spec(commonJsonSpec).body(payload).when().post("/register");
		assertEquals(responseRegisFailedUsername.statusCode(), 400);
		
		message = responseRegisFailedUsername.jsonPath().get("message");
		System.out.println("ERROR KENAPA ? "+ ANSI_WHITE_BACKGROUND + ANSI_PURPLE  + message + "\n" + ANSI_RESET);
	}
	
	//kalau null masih bisa created ternyata jadi ga mandatory
}

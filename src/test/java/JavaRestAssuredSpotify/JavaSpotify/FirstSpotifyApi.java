package JavaRestAssuredSpotify.JavaSpotify;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class FirstSpotifyApi {
	
	String token = "Bearer BQA5KvOagm-CkDm1lSYb8fImLUN5uO9QTwXzpPG_GI5QYBBKDjglKmfIT3zoH-PMhqGN8rRwNgV83qsofiG6WqAB1lwK250K5BshMtvmCVbsP4E6f9CAGLDn7tikxDqfyMC0Jf1UwOayQyz0XW-DlcuGZkKUE-mc63hNUmNQcJSlonVaTfpujxRebEaVrQG8K8piHYvnS6o21juZJ0A225Qb0SOOCaqzNa9HMobgk_gd0UOz";
	
@Test
public void testGetUserProfile_StatusCode200() {
	Response responseType = given().contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Authorization",token)
			.get("https://api.spotify.com/v1/me");
	responseType.prettyPrint();
	int statusCode = responseType.getStatusCode();
	System.out.println("Resulting Status Code = " +statusCode);
}

@Test
public void testGetTrackSearch_StatusCode200() {
	
	RequestSpecification httpRequest = RestAssured.given();
	httpRequest.queryParam("q", "pitbull");
	httpRequest.queryParam("type", "track");
	httpRequest.header("Authorization", token);
	Response response = httpRequest.request(Method.GET,"https://api.spotify.com/v1/search");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
}

@Test
public void testCreatePlaylist_StatusCodeReturn201() {
	RequestSpecification httpRequest = RestAssured.given();
	
	JSONObject requestParam = new JSONObject();
	requestParam.put("name", "With Java Spotify");
	requestParam.put("description", "Java Playlist description");
	requestParam.put("public", "false");
	httpRequest.header("Authorization", token);
	httpRequest.body(requestParam.toString());
	Response response = httpRequest.request(Method.POST,"https://api.spotify.com/v1/users/31ai2edlanv3lrfetwv725a2jix4/playlists");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
}

@Test
public void addTracksToPlaylist_StatusCodeReturn201() {
	RequestSpecification httpRequest = RestAssured.given();
	httpRequest.queryParam("uris","spotify:track:6U6Lieem2VpReKCxyvDeRs","spotify:track:0Oe49j06Bjrxs8PltuVeaW");
	httpRequest.header("Authorization", token);

	Response response = httpRequest.request(Method.POST,"https://api.spotify.com/v1/playlists/4WsS0cBajmK5mHnCcoSxh0/tracks");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
}

@Test
public void testChangePlaylistDetails_StatusCodeReturn200() {
	
	String requestBody = "{\r\n  \"name\": \"Java Updated Playlist Name\",\r\n  \"description\": \"Java updated playlist description\",\r\n  \"public\": false\r\n}";
	RequestSpecification httpRequest = RestAssured.given()
			.header("Content-type","application/json")
			.and()
			.body(requestBody);
	httpRequest.header("Authorization", token);
	Response response = httpRequest.request(Method.PUT,"https://api.spotify.com/v1/playlists/4WsS0cBajmK5mHnCcoSxh0");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
}

@Test
public void testDeleteSongs_StatusCodeReturn200() {
	String requestBody = "{\r\n\"tracks\":[\r\n {\r\n\"uri\":\"spotify:track:6U6Lieem2VpReKCxyvDeRs\",\r\n\"positions\":[\r\n" +9+ "\r\n]\r\n        },\r\n\r\n        {\r\n            \"uri\" : \"spotify:track:6U6Lieem2VpReKCxyvDeRs\",\r\n            \"positions\" : [\r\n                "+11+"\r\n            ]\r\n        }\r\n    ]\r\n}";
	RequestSpecification httpRequest = (RequestSpecification)RestAssured.given()
			.header("Content-type","application/json")
			.and()
			.body(requestBody);
	httpRequest.header("Authorization", token);
	Response response = httpRequest.request(Method.DELETE,"https://api.spotify.com/v1/playlists/4WsS0cBajmK5mHnCcoSxh0/tracks");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
}
}

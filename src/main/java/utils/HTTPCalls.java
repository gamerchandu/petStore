package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HTTPCalls {

    public Response post(String inputRequest, String inputURL){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(inputRequest)
                .post(inputURL);
    }

    public Response put(String inputRequest, String inputURL){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(inputRequest)
                .post(inputURL);
    }

    public Response get( String inputURL){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .get(inputURL);
    }
}

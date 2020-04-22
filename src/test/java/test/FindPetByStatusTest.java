package test;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import utils.HTTPCalls;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPetByStatusTest {

    public Response response;

    public String status = "available";
    public HTTPCalls httpCalls = new HTTPCalls();

    public Response callService() {
      return response= httpCalls.get("https://petstore.swagger.io/v2/pet/findByStatus?status="+status);
    }

    @Test
    public void getServiceStatus(){
        callService();
        response.then().assertThat().statusCode(200);
    }

}

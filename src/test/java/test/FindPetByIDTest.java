package test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.HTTPCalls;

public class FindPetByIDTest {

    public Response response;

    public HTTPCalls httpCalls = new HTTPCalls();

    public Response callService(int id) {
      return response= httpCalls.get("https://petstore.swagger.io/v2/pet/"+id);
    }


    @Test
    public void getServiceStatus(){
        int id= 1;
        callService(id);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void verifyInvalidPet(){
        int id= 1345345;
        callService(id);
        response.then().assertThat().statusCode(404);
    }

}

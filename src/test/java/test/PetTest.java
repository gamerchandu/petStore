package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PetTest    {

    public Response response;

    public String categoryName = "dogs";
    public String name = "germanShepard";
    public String tagName = "gs";
    public String status = "available";
    public  int id=1;
    public  int categoryId=2;
    public  int tagId=3;

    @BeforeEach
    public void post() {

        JSONObject    requestInput = new JSONObject();
        requestInput.put("id",id);

        JSONObject catArray = new JSONObject();
        catArray.put("id",categoryId);
        catArray.put("name",categoryName);

        requestInput.put("category",catArray);

        requestInput.put("name",name);
        JSONArray photoAr = new JSONArray();
        photoAr.add("string");
        requestInput.put("photoUrls",photoAr);

        JSONObject tagArray = new JSONObject();
        tagArray.put("id",tagId);
        tagArray.put("name",tagName);

        JSONArray tagAr = new JSONArray();
        tagAr.add(tagArray);

        requestInput.put("tags",tagAr);

        requestInput.put("status",status);

       // System.out.println(requestInput.toJSONString());

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(requestInput.toJSONString())
                .post("https://petstore.swagger.io/v2/pet");
    }

    public void addHeaders(){

    }

    @Test
   public void getID(){
        response.then().assertThat().statusCode(200);

        assertEquals(Integer.valueOf(response.getBody().jsonPath().getJsonObject("id").toString()), id);
    }

    @Test
    public void getCategory(){
        response.then().assertThat().statusCode(200);
        assertEquals(response.getBody().jsonPath().getJsonObject("category.name"), categoryName);
        assertEquals(Integer.valueOf(response.getBody().jsonPath().getJsonObject("category.id").toString()), categoryId);
    }

    @Test
    public void getPhotoURL(){
        response.then().assertThat().statusCode(200);
        assertEquals(response.getBody().jsonPath().getJsonObject("photoUrls[0]"), "string");
    }

    @Test
    public void getTags(){
        response.then().assertThat().statusCode(200);
        assertEquals(response.getBody().jsonPath().getJsonObject("tags.name[0]"), tagName);
        assertEquals(Integer.valueOf(response.getBody().jsonPath().getJsonObject("tags.id[0]").toString()), tagId);


    }

    @Test
    public void getStatus(){
        response.then().assertThat().statusCode(200);
        assertEquals(response.getBody().jsonPath().getJsonObject("status"), status);
    }


}
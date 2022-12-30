package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.json.simple.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FirstClass {

    @Test
    public void verifyTest(){
        Response res=
                get("https://reqres.in/api/users?page=2");
        System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test
    public void verifyTest1(){
        baseURI="https://reqres.in/api";
        given()
                .get("/users?page=2")
        .then()
                .statusCode(200)
                .body("data[1].id",equalTo(8))
                ;
    }

    @Test
    public void testPost(){
        /*Map<String,Object> mapBody=new HashMap<>();
        mapBody.put("name","TusharA");
        mapBody.put("job","Engg");
        System.out.println(mapBody);*/

        JSONObject jsonObj=new JSONObject();
        jsonObj.put("name","TusharA");
        jsonObj.put("job","Engg");
        System.out.println(jsonObj.toJSONString());

        baseURI="https://reqres.in/api";

        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObj.toJSONString())
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .log().all();

    }
}

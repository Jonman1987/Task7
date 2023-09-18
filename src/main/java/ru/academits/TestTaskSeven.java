package ru.academits;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import org.asynchttpclient.util.Assertions;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestTaskSeven {

    @Test
    public void getStatusCodePositive() {
        Response response = RestAssured
                .given()
                .body("{\n" + "\t\"name\":\"evgeny\",\n" + "\t\"job\":\"driver\",\n" + "}")
                .when()
                .get("https://reqres.in/api/users")
                .andReturn();
        response.prettyPrint();
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

   @Test
    public void getStatusCodeNegative(){
       Response response = RestAssured
               .given()
               .body("{\n" + "\t\"name\":\"evgeny\",\n" + "\t\"job\":\"driver\",\n" + "}")
               .when()
               .get("https://reqres.in/api/users")
               .andReturn();
       response.prettyPrint();
       int statusCode = response.getStatusCode();
       assertEquals(210, statusCode);
    }

   @Test
    public void getAssertPage(){
       JsonPath response = RestAssured
               .given()
               .body("{\n" + "\t\"name\":\"evgeny\",\n" + "\t\"job\":\"driver\",\n" + "}")
               .when()
               .get("https://reqres.in/api/users")
               .jsonPath();
       response.prettyPrint();

       assertEquals("1", response.get("page").toString());
    }

    @Test
    public void getAssertArrayCount(){
        JsonPath response = RestAssured
                .given()
                .body("{\n" + "\t\"name\":\"evgeny\",\n" + "\t\"job\":\"driver\",\n" + "}")
                .when()
                .get("https://reqres.in/api/users")
                .jsonPath();
        response.prettyPrint();

        assertNotEquals("0", response.get("data").toString().length());
    }

    @Test
    public void getAssertTotal(){
        JsonPath response = RestAssured
                .given()
                .body("{\n" + "\t\"name\":\"evgeny\",\n" + "\t\"job\":\"driver\",\n" + "}")
                .when()
                .get("https://reqres.in/api/users")
                .jsonPath();
        response.prettyPrint();

        assertEquals("12", response.get("total").toString());
    }
}

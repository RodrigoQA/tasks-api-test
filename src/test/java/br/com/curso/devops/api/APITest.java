package br.com.curso.devops.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class APITest {


    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void test() {

        RestAssured.given().

                when().get("/todo").
                then().
                statusCode(200).
                log().all();

    }
    @Test
    public void deveAddTaskComSucesso() {

        RestAssured.given().
body("{ \"task\":\"teste api 01\",\"dueDate\":\"2030-02-02\" }").
                contentType(ContentType.JSON).
                when().post("/todo").
                then().
                log().all().
                statusCode(201);


    }

    @Test
    public void naoDeveAddTasksInvalidas() {

        RestAssured.given().
                body("{ \"task\":\"teste api 01\",\"dueDate\":\"2020-02-02\" }").
                contentType(ContentType.JSON).
                when().post("/todo").
                then().
                log().all().
                body("message", CoreMatchers.is("Due date must not be in past")).
                statusCode(400);


    }

}



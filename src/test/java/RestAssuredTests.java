import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.mozilla.javascript.Symbol;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {
//    https://dummy.restapiexample.com/

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "BarrearToken_2341qwdmfdnqk")
                .addCookie("myCookie", "value_1")
                .addCookie("myCookie_2", "value_2")
                .build();
        // To check if header+cookies was added use methods given().log().all() before the method get() below:
    }


    @Test
    public void getAllEmpoyesTest() {
        Response response = RestAssured.given().log().all().get("/employees");
        response.then().statusCode(200);
        response.jsonPath().get("data.findAll{it.employee_salary>10000}.employee_name");
        response.jsonPath().get("data.findAll{it.employee_age<20}.id");
        response.prettyPrint();
    }

    @Test
    public void getSingleEmployeeTest(){
        Response response =  RestAssured.get("/employee/{id}", 4);
        response.then().statusCode(200);
        response.then().body("status", equalTo("success"));
        response.then().body("data.employee_name", equalTo("Cedric Kelly"));
        response.jsonPath().get("data.id");
        response.prettyPrint();
    }

    @Test
    public void createNewEmployeeTest(){

        CreateEmpoyeeBody body = new CreateEmpoyeeBody().builder()
                .name("testBuilderName")
                .salary("6730")
                .age("35")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .post("/create");
        response.prettyPrint();

        response.as(ResponseEmployeeBody.class).getData().getId();
//        If need to get all response body, just use: response.as(ResponseEmployeeBody.class);

    }
}

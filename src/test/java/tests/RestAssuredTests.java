package tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.CreateBookingRequestBody;
import responces.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RestAssuredTests {

    @BeforeMethod
    @Step("Setup enviroment")
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @Step("Login with correct credantions to get a user Token")
    public String getAuthToken() {

        LoginBody body = new LoginBody().builder()
                .username("admin")
                .password("password123")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .post("/auth");
        String token = response.as(LoginResponse.class).getToken();
        response.prettyPrint();
        return token;
    }

    @Step("Get BookingId that will be used in future tests")
    @Description("Get all BookingId and returne one random Booking by Id to use in tests for update")
    public Object getBookingIds() {
        Response response = RestAssured.given().log().all().get("/booking");
        response.then().statusCode(200);
        int i = 0;
        int randomNum = ThreadLocalRandom.current().nextInt(i, i + 1);
        Object BookingId = response.jsonPath().getList("bookingid").get(randomNum);
        return BookingId;
    }
    @Test
    @Description("Check Get All Bookings")
    public void getAllBookings() {
        Response response = RestAssured.get("/booking");
        response.jsonPath().getList("bookingid");
        response.prettyPrint();
    }

    @Step("Create new Booking")
    @Description("Create new Booking")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Number createBooking() {

        Date dateFrom;
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFrom = DateFor.parse("2020-07-10");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dateTo;
        SimpleDateFormat DateTo = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateTo = DateTo.parse("20220-07-10");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        CreateBookingRequestBody body = new CreateBookingRequestBody().builder()
                .firstname("Yuliia")
                .lastname("Sokolova")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(Bookingdates.builder().build().builder()
                        .checkin(dateFrom)
                        .checkout(dateTo)
                        .build())
                .additionalneeds("Additional text")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .post("/booking");

        Number createdBookindId = response.as(CreateBookingResponce.class).getBookingid();
        System.out.println(createdBookindId);
        return createdBookindId;
    }

    @Test
    @Description("Check that Booking was created")
    public void checkThatBookingWasCreated(){
        Number id = createBooking();
        Response response = RestAssured.given().log().all().get("/booking/" + id);
        response.as(GetBookingResponceData.class);
        response.prettyPrint();
    }

    @Test
    @Description("Update random Booking with PATCH")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void UpdateFirstBooking() {
        Object id = getBookingIds();

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String token = getAuthToken();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addCookie("token", token)
                .build();

        UpdateBookingFirstResponce bodyForFirstUpdate = new UpdateBookingFirstResponce().builder()
                .totalprice(6789)
                .build();

        Response responseForFirstUpdate = RestAssured.given().log().all()
                .body(bodyForFirstUpdate)
                .patch("booking/" + id);

        responseForFirstUpdate.prettyPrint();
    }

    @Test
    @Description("Update random Booking with PUT")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void UpdateSecondBooking() {
        Object id = getBookingIds();
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String token = getAuthToken();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addCookie("token", token)
                .build();

        Date dateFrom = null;
        Date dateTo = null;
        UpdateBookingSecondResponce bodyForSecondUpdate = new UpdateBookingSecondResponce().builder()
                .firstname("Yuliia")
                .lastname("Sokolova")
                .totalprice(876756)
                .depositpaid(false)
                .bookingdates(Bookingdates.builder()
                        .checkin(dateFrom)
                        .checkout(dateTo)
                        .build())
                .additionalneeds("Additional text")
                .build();

        Response responseForSecondUpdate = RestAssured.given().log().all()
                .body(bodyForSecondUpdate)
                .put("booking/" + id);

        responseForSecondUpdate.prettyPrint();
    }

    @Test
    @Description("Delete Booking that was crteated")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void DeleteCreatedBooking() {
        Object id = createBooking();

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String token = getAuthToken();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addCookie("token", token)
                .build();

        Response responseForFirstUpdate = RestAssured.given().log().all()
                .delete("booking/" + id);

        responseForFirstUpdate.prettyPrint();
    }
}

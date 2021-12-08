import com.abc.utility.BaseTest;
import com.abc.requestsPOJO.BookingDates;
import com.abc.requestsPOJO.BookingDetails;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given; //import this

public class CreateBooking extends BaseTest {

    public static String newID = "";


    @Test
    public void createNewBooking(){

        String firstname="Mary";
        String lastname="Mary";
        String totalprice="10";
        boolean depositpaid=true;
        String  checkin="2020-04-04";
        String checkout="2020-10-10";
        String additionalneeds="Breakfast";

        /*******************************************************
         * Send a POST request to /booking/{id}
         * and check that the response has HTTP status code 200
         ******************************************************/

        //Sending the GET request for a specific booking id and receiving the response

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setFirstname(firstname);
        bookingDetails.setLastname(lastname);
        bookingDetails.setTotalprice(Integer.parseInt(totalprice));
        bookingDetails.setDepositpaid(depositpaid);
        bookingDetails.setAdditionalneeds(additionalneeds);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        bookingDetails.setBookingdates(bookingDates);

        Response response = given().
                spec(requestSpec).
                contentType("application/json").
                body(bookingDetails).log().body().
                when().
                post("/booking");

        //Verify the response code
        response.then().spec(responseSpec);

        //To log the response to report
        logResponseAsString(response);


        //To get the newly created bookign id
       // System.out.println(response.then().extract().path("bookingid"));

        newID = response.then().extract().path("bookingid").toString();
        System.out.println(newID);

    }
}

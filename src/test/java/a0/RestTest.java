package a0;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Calendar;


public class RestTest {

    @BeforeAll
    static void setup() throws Exception{
        Main.main(null); 
    }




    /**
     * Tests GET/date.     
     */
    @Test
    void testGetDate() {
        int date = Calendar.getInstance().get(Calendar.DATE);
        when().get("/date").then().statusCode(200).body(equalTo(date));
    }

    /**
     * Tests GET/date bad.
     */
    @Test
    void testGetDate2() {
        int date = Calendar.getInstance().get(Calendar.DATE);
        when().get("/date/now").then().
        statusCode(404).
        body(equalTo("Invalid format."));
    }

    /**
     * Tests GET/day.
     */
    @Test
    void testGetDay() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        when().get("/day").then().statusCode(200).body(equalTo(day));
    }

    /**
     * Tests GET/day bad.
     */
    @Test
    void testGetDay2() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        when().get("/day/now").then().statusCode(400).
        body(equalTo("Invalid format."));
    }

    /**
     * Tests GET/month.
     */
    @Test
    void testGetMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        when().get("/month").then().statusCode(200).body(equalTo(month));
    }

    /**
     * Tests GET/month bad.
     */
    @Test
    void testGetMonth2() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        when().get("/month/now").then().statusCode(400).
        body(equalTo("Invalid format."));
    }

    /**
     * Tests GET/year.
     */
    @Test
    void testGetYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        when().get("/year").then().statusCode(200).body(equalTo(year));
    }

    /**
     * Tests GET/year bad.
     */
    @Test
    void testGetYear2() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        when().get("/year/now").then().statusCode(400).
        body(equalTo("Invalid format."));
    }

    /**
     * Tests POST/event/{eventId}.
     */
    @Test
    void testAddEvent() {
        when().post("/event?event=meet with Ms Gu&year=2022&month=11&day=3&date=16").then().statusCode(200).
        body(equalTo("Succeed."));
    }

    /**
     * Tests POST/event/{eventId} bad.
     */
    @Test
    void testAddEvent2() {
        when().post("/event").then().statusCode(400).
        body(equalTo("Failed. Invalid input."));
    }

    /**
     * Tests DELETE/event/{eventId}.
     */
    @Test
    void testDeleteEvent() {
        when().delete("/event?event=meet with Ms Gu&year=2022&month=11&day=3&date=16").then().statusCode(200).
        body(equalTo("Succeed."));
    }

    /**
     * Tests DELETE/event/{eventId} bad.
     */
    @Test
    void testDeleteEvent2() {
        when().delete("/event/99").then().statusCode(400).
        body(equalTo("Failed. Invalid input."));
    }

    /**
     * Tests Get/event/{date}.
     */
    @Test
    void testGetEvent() {

        when().get("/event?&year=2022&month=11&day=3&date=16").then().statusCode(200).
        body(equalTo("Succeed. event: meet with Ms Gu"));
    }

    /**
     * Tests Get/event/{date} bad.
     */
    @Test
    void testGetEvent2() {
        when().get("/event/{date}").then().statusCode(400).
        body(equalTo("Failed. Invalid input."));
    }
}

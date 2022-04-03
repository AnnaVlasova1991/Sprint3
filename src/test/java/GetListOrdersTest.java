import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetListOrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @Test
    public void loginCourierAndCheckSuccessResponse() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .when()
                        .get("/api/v1/orders");
        response.then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    DataOrder dataOrder;
    int responseCode;
    int track;


    public CreateOrderTest(DataOrder dataOrder, int responseCode) {
        this.dataOrder = dataOrder;
        this.responseCode = responseCode;
    }

    @Parameterized.Parameters
    public static Object[] dataForTest() {
        return new Object[][] {
                {new DataOrder("Naruto", "Uzumaki", "Konoha, 142 apt.", 5,
                        "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", Arrays.asList("BLACK")), 201},
                {new DataOrder("Itachi", "Uchiha", "Konoha, 143 apt.", 7,
                        "+7 900 345 36 76", 10, "2022-03-05", "Amaterasu!!!", Arrays.asList("BLACK", "GREY")), 201},
                {new DataOrder("Madara", "Uchiha", "Konoha, 148 apt.", 10,
                        "+7 945 854 86 86", 1, "2022-02-18", "lalala", Arrays.asList("")), 201},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @After
    public void orderDelete() {
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.deleteOrder(track);
    }

    @Test
    public void createOrderAndCheckSuccessResponsePositive() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(dataOrder)
                        .when()
                        .post("/api/v1/orders");
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(responseCode);
        TrackOrder trackOrder = response.body().as(TrackOrder.class);
        track = trackOrder.getTrack();
    }
}

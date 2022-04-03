import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderForNumberTest {
    int track;

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
    public void getOrderSuccessCheckResponse() {
        CreateOrder createOrder = new CreateOrder();
        track = createOrder.createOrderAndReturnTrack();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .queryParam("t", track)
                        .get("/api/v1/orders/track");
        response.then().assertThat().body("order", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void getOrderWithoutNumber() {
        CreateOrder createOrder = new CreateOrder();
        track = createOrder.createOrderAndReturnTrack();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .get("/api/v1/orders/track?t=");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(400);
    }

    @Test
    public void getOrderUncorrectNumber() {
        CreateOrder createOrder = new CreateOrder();
        track = createOrder.createOrderAndReturnTrack();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .get("/api/v1/orders/track?t=" + new Random().nextInt(100));
        response.then().assertThat().body("message", equalTo("Заказ не найден"))
                .and()
                .statusCode(404);
    }

}

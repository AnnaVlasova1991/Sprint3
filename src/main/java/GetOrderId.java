import static io.restassured.RestAssured.given;

public class GetOrderId {
    public int getOrderId(int track) {
        CreateOrder createOrder = new CreateOrder();
        OrderForGetIdAll orderForGetIdAll =
                given()
                        .header("Content-type", "application/json")
                        .get("https://qa-scooter.praktikum-services.ru/api/v1/orders/track?t=" + track)
                        .body().as(OrderForGetIdAll.class);
        return orderForGetIdAll.getOrder().getId();
    }
}

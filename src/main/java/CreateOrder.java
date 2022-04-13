import io.restassured.response.Response;
import model.DataOrder;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    public int createOrderAndReturnTrack() {
        String baseUrl = "https://qa-scooter.praktikum-services.ru";
        OrderDataGenerator orderDataGenerator = new OrderDataGenerator();
        String firstName = orderDataGenerator.getFirstName();
        String lastName = orderDataGenerator.getLastName();
        String address = orderDataGenerator.getAddress();
        int metroStation = orderDataGenerator.getMetroStation();
        String phone = orderDataGenerator.getPhone();
        int rentTime = orderDataGenerator.getRentTime();
        String deliveryDate = orderDataGenerator.getDeliveryDate();
        String comment = orderDataGenerator.getComment();
        List<String> color = orderDataGenerator.getColor();
        DataOrder dataOrder = new DataOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(dataOrder)
                .when()
                .post(baseUrl + "/api/v1/orders");
        TrackOrder trackOrder = response.body().as(TrackOrder.class);

        return trackOrder.getTrack();
    }
}
